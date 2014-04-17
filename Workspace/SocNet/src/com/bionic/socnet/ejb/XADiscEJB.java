package com.bionic.socnet.ejb;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.resource.ResourceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.UploadedFile;
import org.xadisk.bridge.proxies.interfaces.XAFileOutputStream;
import org.xadisk.connector.outbound.XADiskConnection;
import org.xadisk.connector.outbound.XADiskConnectionFactory;
import org.xadisk.filesystem.exceptions.ClosedStreamException;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.FileUnderUseException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

@Stateless
public class XADiscEJB {
	@Resource(lookup = "java:global/XADiskCF", type = org.xadisk.connector.outbound.XADiskConnectionFactoryImpl.class)
	private XADiskConnectionFactory xaConnectionFactory;

	public void write(String path, UploadedFile photo)
			throws FileAlreadyExistsException, FileNotExistsException,
			InsufficientPermissionOnFileException, LockingFailedException,
			NoTransactionAssociatedException, InterruptedException,
			IOException, FileUnderUseException, ResourceException,
			ClosedStreamException {

		XADiskConnection conn = xaConnectionFactory.getConnection();
		File file = new File("SocNetPhotos/" + path);
		conn.createFile(file, false);
		InputStream inputStream = photo.getInputstream();
		XAFileOutputStream outputStream = conn.createXAFileOutputStream(file,
				false);
		byte[] buf = new byte[1024];
		while (inputStream.read(buf) >= 0) {
			outputStream.write(buf);
		}
	}

	private void resize(int maxHeight, int maxWidth, InputStream inputStream,
			OutputStream outputStream) throws IOException {
		BufferedImage originalImage = ImageIO.read(inputStream);
		double multiple = 1;
		int height = originalImage.getHeight();
		int width = originalImage.getWidth();
		if (((double) maxHeight / (double) maxWidth) < ((double) height / (double) width)) {
			multiple = (double) maxHeight / (double) height;
		} else {
			multiple = (double) maxWidth / (double) width;
		}
		if (multiple < 1) {
			height = (int) (originalImage.getHeight() * multiple);
			width = (int) (originalImage.getWidth() * multiple);
		}
		BufferedImage scaledImage = new BufferedImage(width, height,
				originalImage.getType());
		Image im = originalImage.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		Graphics2D g = scaledImage.createGraphics();
		g.drawImage(im, null, null);
		ImageIO.write(scaledImage, "JPG", outputStream);
	}

	public void read(HttpServletRequest request, HttpServletResponse response) {
		try {
			String height = request.getParameter("height");
			String width = request.getParameter("width");
			String path = request.getParameter("path");
			OutputStream outputStream = response.getOutputStream();
			File file = new File("SocNetPhotos/" + path);
			FileInputStream inputStream = new FileInputStream(file);
			if (height != null && width != null) {
				resize(Integer.parseInt(height), Integer.parseInt(width),
						(InputStream) inputStream, outputStream);
			} else {
				byte[] buf = new byte[1024];
				int count = 0;
				while ((count = inputStream.read(buf)) >= 0) {
					outputStream.write(buf, 0, count);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
