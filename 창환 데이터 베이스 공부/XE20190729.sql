select ename,salary from employee where salary >
(select salary from employee where ename =  'SCOTT'); -- 소괄호 친부분이 서브쿼리이다.


select ename,salary from employee where dno =
(select dno from employee where ename =  'SCOTT');

select eno, ename,salary from employee where salary in 
(select min(salary) from employee group by dno);

select DISTINCT salary from employee where job = 'SALESMAN';

select eno,ename,job,salary from employee where salary < any(1250,1600,1500)
and job!='SALESMAN';



select eno,ename,job,salary from employee where salary < --서브쿼리 활용한 것
any(select DISTINCT salary from employee where job = 'SALESMAN')
and job!='SALESMAN';

select eno,ename,job,salary from employee where salary < 
all (select salary from employee where job = 'SALESMAN')and job<> 'SALESMAN';

select sigunguk from zipcode where sidok = '서울특별시' group by sigunguk order by sigunguk;


select sigunguk, count(DISTINCT law_name) from zipcode 
where sidok = '서울특별시' group by sigunguk order by sigunguk;

select bd_name from zipcode 
where sidok = '서울특별시' and bd_name like '%중학교';

select sigunguk,law_name,count(*) from zipcode 
where sidok = '서울특별시' and bd_name like '%중학교%' group by sigunguk,law_name;

--Chater 07_서브쿼리
SELECT eno,ename,job FROM employee where eno =  --1번
(select eno from employee where eno ='7788');

select eno,ename,salary from employee  --2번
where salary > (select salary from employee where eno ='7499');

select dno,ename,min(salary) from employee  --3번
where salary = (select min(salary) from employee) 
group by dno, ename;

select job,round(avg(salary)) from employee group by job having round(avg(salary)) = --4번
(select min(round(avg(salary))) from employee GROUP by job);

select ename,dno,salary from employee where salary in --5번
(select min(salary) from employee group by dno) order by dno asc;

select dno,ename,salary from employee where(dno,salary) in ((30,950),(20,800),(10,1300)); --5번

select job,ENAME,SALARY from employee where salary < --6번
any(select salary from employee where job = 'ANALYST') and job<>'ANALYST';

select * from employee;
select * from department;


select ename from employee where eno in --7번
(select eno from employee where manager is null);

select ename from employee where eno in --8번
(select eno from employee where manager is not null);

select ename,hiredate from employee where dno = --9번
(select dno from employee where ename = 'BLAKE') and ename<>'BLAKE';

select eno,ename,salary from employee where salary > --10번
(select round(avg(salary)) from employee) order by salary asc;

select eno,ename from employee where dno in --11번
(select dno from employee where ename like '%K%');

select dno,eno,ename from employee where dno = --12번
(select dno from department where loc = 'DALLAS');

select ename,salary,dno from employee where manager = --13번
(select eno from employee where ename='KING');

select DNO,ename,job from employee where dno = --14번
(select DNO from department where DNAME = 'RESEARCH');

select eno,ename,salary from employee where salary> --15번
(select round(avg(salary)) from employee) and ename like '%K%';

select job,avg(salary) from employee group by job having avg(salary) = --16번
(select min(avg(salary)) from employee group by job) ;

select DNO,ENAME,job from employee where dno in  --17번
(select dno from employee where job = 'ANALYST')and job<>'ANALYST';


select * from zipcode;

--서울 인천 같은 주소 찾기
select DISTINCT(law_name) from zipcode where sidok = '서울특별시' and law_name in 
(select DISTINCT(LAW_NAME) from zipcode where sidok = '인천광역시');



select law_name,count(*) from zipcode group by law_name having count(*) > 1;

-- 테이블 생성 수정 제거
create table dept (
dno number(2),
dname varchar2(14),
loc varchar2(13));

desc dept;
create table dept2
as select * from department;
desc dept2
select *from dept2;

create table dept3
as select eno,ename from employee;  -- as는 데이터 복사하는것
desc dept3;
select * from dept3;

desc dept;
alter table dept  -- 테이블 데이터 추가 생성
add(birth date);

alter table dept  -- 컬럼변경은 loc(varchar) 13 ->20 으로  늘리는것만 가능
modify(loc varchar2(20));

alter table dept -- 컬럼 삭제
drop COLUMN birth;

select * from dept3;
desc dept3;
alter table dept3
set unused (ename);

alter table dept3
drop UNUSED columns;

rename dept3 to dept33;

drop table dept2;
drop table dept33;
drop table dept;

select table_name from user_tables;
select owner, table_name from dba_tables;

create table dept
as select *from department where 0=1;

desc dept;
insert into dept (dno,dname,loc)
    values(10,'ACC','NEW');
insert into dept (dno,dname,loc)
    values(20,'ACC2','NEW2');
insert into dept (dno,dname,loc)
    values(30,'ACC3','NEW3');

insert into dept values (10,'ACCOUNTING','NEW YORK');

create table emp2
as select *from employee where 1=2;

desc emp2;

insert into emp2 (eno,hiredate)
values(1,'2019/07/29');
insert into emp2 (eno,hiredate)
values(2,TO_DATE('2019,07,29','YYYY,MM,DD'));
insert into emp2 (eno,hiredate)
values(3,'2019-07-29');
insert into emp2 (eno,hiredate)
values(4,'2019.07.29');

select * from emp2;

create table dept2
as select * from department where 1=2;
select * from dept2;
insert into dept2
select * from department order by dno;

update dept2 set dno = 21
where dname = 'RESEARCH';

update dept2 set dno = 22, dname = 'ACC2', loc='LA'
where dno = 21;

delete dept2 where dno = 22;
select * from emp2;
update emp2 set dno = 30
where eno > 3;

delete emp2 where dno = (select dno from department where dno=30);

create table emp2 as select * from employee;

create table dept2
as select * from department where 1=2;
insert into dept2 select * from department;

select * from dept2;

select * from emp2;
delete from emp2 where eno = 8000;
rollback;
commit;
delete from emp2 where dno = 20;
delete from emp2 where dno = 30;

update emp2 set salary = 0 where dno = 20;

create table customer (id varchar2(20) UNIQUE,
                        pwd varchar2(20) not null,
                        phone varchar2(20));
                        
create table customer2 
(id varchar2(20) constraint customer2_id_uk UNIQUE,
pwd varchar2(20) constraint  customer2_pwd_nn NOT NULL,
phone varchar2(30));
desc customer2;


create Table customer3(
    id varchar2(20),
    phone varchar2(30),
    constraint customer3_id_pk primary key(id));
    
create Table customer4(
    id varchar2(20) primary key,
    phone varchar2(30));
    
create Table customer5(
    id varchar2(20) constraint customer5_id_pk primary key,
    phone varchar2(30));

create table dept9( 
            dno number(2) constraint dept_dno_pk primary key,
            dname varchar(14),
            loc varchar(13)
            );
            
            insert into dept9
            select * from department order by dno;
            SELECT * from dept9;
            insert into dept9 values(NULL,'TEST','TEST'); --같은 값을 넣을수 없다.

create table emp9 (
        eno number(4) constraint emp_eno_pk primary key,
        ename varchar2(10),
        job varchar2(9),
        dno number(2) constraint emp_dno_fk references dept9);
        insert into emp9
        select eno,ename, job,dno from employee order by eno;
        
        select * from emp9;
        --실수로 부서번호를 잘못입력 하는 것 이런것을 막아주는것이 forignkey
        --값의 중복을 막는것 
        insert into emp9 values(8001,'홍길동1','점원',10);
        insert into emp9 values(8002,'홍길동1','점원',50); 
        delete from dept9 where dno = 30; -- 서로간에 데이터를 참조하고 있기때문에 지울수 없다.
        
