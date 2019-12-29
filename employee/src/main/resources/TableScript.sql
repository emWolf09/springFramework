drop table address;
drop table employee;


CREATE TABLE employee(
	employee_id BigInt(10) not null auto_increment,
	first_name varchar(20) not  null,
	last_name varchar(20) not  null,
	primary key (employee_id)

);

ALTER TABLE employee AUTO_INCREMENT = 1001;

CREATE TABLE address(
	address_id BigInt(10) not null AUTO_INCREMENT,
	emp_id BigInt(10),
	street varchar(40) not  null,
	city varchar(20) not null,
	state varchar(20) not null,
	country varchar(20) not null,
	pincode varchar(20) not null,
	primary key (address_id),
	foreign key (emp_id) references employee(employee_id)

);

ALTER TABLE address AUTO_INCREMENT = 5001;

truncate table address;
delete from employee where true;

INSERT INTO employee (employee_id, first_name, last_name) VALUES (1001,'ram', 'sharma');
INSERT INTO employee (employee_id, first_name, last_name) VALUES (1002,'shyam', 'varma');
INSERT INTO employee (employee_id, first_name, last_name) VALUES (1003,'jhon', 'davis');
INSERT INTO employee (first_name, last_name) VALUES ('raju', 'rastogi');

INSERT INTO address (address_id, street, city,state,country,pincode,emp_id) VALUES (5001,'14th cross neeladri', 'Bangalore','Karnataka','India','560100',1001);
INSERT INTO address (address_id, street, city,state,country,pincode,emp_id) VALUES (5002,'uniworld marathalli', 'Bangalore','Karnataka','India','560101',1001);
INSERT INTO address (address_id, street, city,state,country,pincode,emp_id) VALUES (5003,'house 4 101 14th cross neeladri', 'Bangalore','Karnataka','India','785645',1002);
INSERT INTO address (address_id, street, city,state,country,pincode,emp_id) VALUES (5004,'BhootVilla  whitefield', 'Jaipur','Rajasthan','India','302017',1003);
INSERT INTO address (street, city,state,country,pincode,emp_id) VALUES ('nainwa malviya', 'Jaipur','Rajasthan','India','302017',1003);

 
commit;


select * from employee;
select * from address;



