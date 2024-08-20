
CREATE TABLE User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(100),
    role ENUM('Student', 'Professor', 'Admin') NOT NULL
);
CREATE TABLE Student (
    student_id INT PRIMARY KEY,
    department VARCHAR(50),
    isApproved BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (student_id) REFERENCES User(user_id)
);

CREATE TABLE Professor (
    professor_id INT PRIMARY KEY,
    department VARCHAR(50),
    designation VARCHAR(50),
    FOREIGN KEY (professor_id) REFERENCES User(user_id)
);

CREATE TABLE Admin (
    admin_id INT PRIMARY KEY,
    date_of_joining DATE,
    FOREIGN KEY (admin_id) REFERENCES User(user_id)
);


CREATE TABLE Course (
    course_id VARCHAR(10) PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    professor_id INT,
    total_seats INT,
    available_seats INT,
    is_offered BOOLEAN,
    FOREIGN KEY (instructor_id) REFERENCES Professor(professor_id)
);


CREATE TABLE CourseEnrollment (
    student_id INT,
    course_id VARCHAR(20),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);


CREATE TABLE GradeCard (
    student_id INT,
    course_id VARCHAR(50),
    grade VARCHAR(2),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (course_id) REFERENCES Course(course_id)
);

CREATE TABLE SystemSettings (
    id INT PRIMARY KEY,
    is_add_drop_window_open BOOLEAN
);

INSERT INTO SystemSettings (id, is_add_drop_window_open) VALUES (1, false);

CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    amount_due DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(student_id)
);


INSERT INTO User (username, password, name, role) VALUES
('student1', 'password1', 'Aditya', 'Student'),
('student2', 'password1', 'Harry', 'Student'),
('student3', 'password1', 'Suhani', 'Student'),
('student4', 'password1', 'Keith', 'Student'),
('professor1', 'password1', 'Russek', 'Professor'),
('professor2', 'password1', 'Jake', 'Professor'),
('admin1', 'password1', 'Harry', 'Admin');

INSERT INTO Student (student_id, department) VALUES (1, 'Computer Science');
INSERT INTO Student (student_id, department) VALUES (2, 'Computer Science');
INSERT INTO Student (student_id, department) VALUES (3, 'Computer Science');
INSERT INTO Student (student_id, department) VALUES (4, 'Computer Science');
INSERT INTO Professor (professor_id, department, designation) VALUES (5, 'Computer Science', 'Associate Professor');
INSERT INTO Professor (professor_id, department, designation) VALUES (6, 'Computer Science', 'Professor');
INSERT INTO Admin (admin_id, date_of_joining) VALUES (7, '2024-01-01');

INSERT INTO Course (course_id, course_name, professor_id, total_seats, available_seats, is_offered) VALUES
('C101', 'Introduction to Computer Science', 5, 10, 9, true),
('C102', 'Data Structures', 5, 10, 9, true),
('C103', 'Database Systems', 6, 10, 9, true),
('C104', 'Operating Systems', 6, 10, 9, true);
INSERT INTO Course (course_id, course_name, professor_id, total_seats, available_seats, is_offered) VALUES
('E101', 'Power Systems', null, 10, 10, false);

INSERT INTO Course (course_id, course_name, professor_id, total_seats, available_seats, is_offered) VALUES
('E102', 'Signal Systems', null, 10, 10, true);

INSERT INTO CourseEnrollment (student_id, course_id) VALUES (1, 'C101');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (1, 'C102');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (1, 'C103');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (1, 'C104');


-- select * from course;
-- select * from CourseEnrollment;
select * from user;
select * from systemsettings;
select * from Professor;
select * from student;

CREATE TABLE Demo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT,
    gender VARCHAR(50),
    email VARCHAR(100)
);
select * from demo;

