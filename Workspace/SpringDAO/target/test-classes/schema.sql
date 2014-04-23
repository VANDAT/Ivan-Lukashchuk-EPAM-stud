CREATE TABLE IF NOT EXISTS Employee(
    id IDENTITY,
    name varchar(30),
  	job_id int
);

CREATE TABLE IF NOT EXISTS Job(
    id IDENTITY,
    name varchar(30)
);
