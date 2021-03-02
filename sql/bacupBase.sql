--------------------------------------------------------
--  File created - четверг-февраля-25-2021
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence EXAM_RECORD_ID
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."EXAM_RECORD_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 221 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQ_EXAM_ID
--------------------------------------------------------

   CREATE SEQUENCE  "ADMIN"."SQ_EXAM_ID"  MINVALUE 1 MAXVALUE 999 INCREMENT BY 5 START WITH 110 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQ_FACULTY_ID
--------------------------------------------------------

  CREATE SEQUENCE  "ADMIN"."SQ_FACULTY_ID"  MINVALUE 1 MAXVALUE 1000 INCREMENT BY 10 START WITH 201 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
  CREATE SEQUENCE  "ADMIN"."SQ_STUDENT_ID"  MINVALUE 10000 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10200 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
  CREATE SEQUENCE  "ADMIN"."SQ_TEACHER_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

  CREATE TABLE "ADMIN"."APPLICANTS"
   (	"ID" NUMBER(*,0),
	"FIRST_NAME" VARCHAR2(30 BYTE) COLLATE "USING_NLS_COMP",
	"LAST_NAME" VARCHAR2(30 BYTE) COLLATE "USING_NLS_COMP",
	"SCHOOL_AVG_SCORES" NUMBER(*,0),
	"FACULTY_ID" NUMBER,
	"ST_EMAIL" VARCHAR2(50 BYTE) COLLATE "USING_NLS_COMP",
	"ST_PASSWORD" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP",
	"ENROLLED" VARCHAR2(1 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" ;
--------------------------------------------------------
--  DDL for Table EXAMINATION_RECORDS
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EXAMINATION_RECORDS"
   (	"STUDENT_ID" NUMBER,
	"EXAM_ID" NUMBER,
	"GRADE" NUMBER,
	"DATETIME" DATE,
	"EXAM_RECORD_ID" NUMBER
   )  DEFAULT COLLATION "USING_NLS_COMP" ;
--------------------------------------------------------
--  DDL for Table EXAMS_FACULTY
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EXAMS_FACULTY"
   (	"FACULTY_ID" NUMBER,
	"EXAM_ID" NUMBER
   )  DEFAULT COLLATION "USING_NLS_COMP" ;
--------------------------------------------------------
--  DDL for Table EXAMS_LIST
--------------------------------------------------------

  CREATE TABLE "ADMIN"."EXAMS_LIST"
   (	"EXAM_ID" NUMBER,
	"EXAM_NAME" VARCHAR2(40 BYTE) COLLATE "USING_NLS_COMP"
   )  DEFAULT COLLATION "USING_NLS_COMP" ;
--------------------------------------------------------
--  DDL for Table FACULTY_LIST
--------------------------------------------------------

  CREATE TABLE "ADMIN"."FACULTY_LIST"
   (	"FACULTY_ID" NUMBER,
	"FACULTY_NAME" VARCHAR2(40 BYTE) COLLATE "USING_NLS_COMP",
	"FACULTY_CAPACITY" NUMBER,
	"FACULTY_MIN_GRADE" NUMBER
   )  DEFAULT COLLATION "USING_NLS_COMP" ;
--------------------------------------------------------
--  DDL for View TOTAL_MARK
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "ADMIN"."TOTAL_MARK" ("ID", "ENROLLED", "FACULTET", "TOTALMARK") DEFAULT COLLATION "USING_NLS_COMP"  AS
  select
            a.student_id as id,
            b.enrolled as enrolled,
            b.faculty_id as facultet,
            sum(a.grade) + b.school_avg_scores as TotalMark
        from
            examination_records a,
            applicants b
        where
            a.student_id = b.id
        group by
            a.student_id, b.school_avg_scores, b.faculty_id, b.enrolled
;
REM INSERTING into ADMIN.APPLICANTS
SET DEFINE OFF;
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10000','Alexey','Nadenenko','79','10','123@dsjhf.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10001','Gleb ','Malahov','67','10','123@qw.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10002','Nikolay','Lukashenko','25','10','luka@shen.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10003','Igor','Sechin','47','10','sechin@loh.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10004','Emmanuel','Macron','85','10','makron@sobaka.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10005','Francois','Olland','65','10','ollando@re.fr','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10006','Nicolas','Sarkozy','90','10','Sarkozy@telki.fr','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10007','Jacques','Chirac','95','10','Chirac@war.fr','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10008','Boris','Jonson','38','10','Boris@redhead.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10009','Teresa','May','50','20','teresa@may.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10010','David','Cameron','63','20','David@titanik.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10011','Gordon','Brown','59','20','Gordom@neramsay.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10012','Tony','Blar','80','20','Tony@blar.com','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10013','John','Major','70','20','John@major.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10014','Margaret','Teatcher','78','20','Margo@telki.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10120','Petr','First','56','10','petr@spb.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10121','Ekaterina','Second','56','20','prostitutki@spb.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10122','Pavel','Third','76','30','pavel@spb.ru','1232','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10123','Nikolay','Second','45','40','rasstrel@zcar.ru','1234','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10124','Vladimir','Lenin','78','30','lenin@zhiv.ru','4542','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10125','Iosif','Stalin','34','40','genozvale@ussr.ru','3123','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10126','George','Malenkov','67','20','anybody@ussr.com','1245','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10127','Nikita','Hrushev','34','10','kukuruzka@ussr.us','5643','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10128','Leo','Brezhnev','56','30','leo@netitanik.ru','4356','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10129','Jura','Andropov','34','40','andro@pov.ru','8732','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10130','Kostya','Chernenko','56','30','cher@nen.co','5657','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10131','Michael','Gorbachev','78','40','Gorba@chev.ru','3456','N');
Insert into ADMIN.APPLICANTS (ID,FIRST_NAME,LAST_NAME,SCHOOL_AVG_SCORES,FACULTY_ID,ST_EMAIL,ST_PASSWORD,ENROLLED) values ('10105','Boris','Eltsin','10','10','borja@propilvse.ru','5325','N');
REM INSERTING into ADMIN.EXAMINATION_RECORDS
SET DEFINE OFF;
REM INSERTING into ADMIN.EXAMS_FACULTY
SET DEFINE OFF;
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('10','10');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('10','15');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('10','40');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('20','10');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('20','40');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('20','20');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('30','15');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('30','20');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('30','40');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('40','40');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('40','10');
Insert into ADMIN.EXAMS_FACULTY (FACULTY_ID,EXAM_ID) values ('40','20');
REM INSERTING into ADMIN.EXAMS_LIST
SET DEFINE OFF;
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('10','Comp Science');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('15','Mathematics');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('20','English language');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('25','Chemistry');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('30','Physics');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('35','Citezenship');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('40','Java Basic');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('45','Economics');
Insert into ADMIN.EXAMS_LIST (EXAM_ID,EXAM_NAME) values ('50','Management');
REM INSERTING into ADMIN.FACULTY_LIST
SET DEFINE OFF;
Insert into ADMIN.FACULTY_LIST (FACULTY_ID,FACULTY_NAME,FACULTY_CAPACITY,FACULTY_MIN_GRADE) values ('10','Computer Science','10','310');
Insert into ADMIN.FACULTY_LIST (FACULTY_ID,FACULTY_NAME,FACULTY_CAPACITY,FACULTY_MIN_GRADE) values ('20','Java Core','10','330');
Insert into ADMIN.FACULTY_LIST (FACULTY_ID,FACULTY_NAME,FACULTY_CAPACITY,FACULTY_MIN_GRADE) values ('30','PHYSICS','10','320');
Insert into ADMIN.FACULTY_LIST (FACULTY_ID,FACULTY_NAME,FACULTY_CAPACITY,FACULTY_MIN_GRADE) values ('40','Mathematics','10','310');
REM INSERTING into ADMIN.TOTAL_MARK
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index EXAMS_LIST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."EXAMS_LIST_PK" ON "ADMIN"."EXAMS_LIST" ("EXAM_ID")
  ;
--------------------------------------------------------
--  DDL for Index FACULTETS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."FACULTETS_PK" ON "ADMIN"."FACULTY_LIST" ("FACULTY_ID")
  ;
--------------------------------------------------------
--  DDL for Index STUDENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."STUDENTS_PK" ON "ADMIN"."APPLICANTS" ("ID")
  ;
--------------------------------------------------------
--  DDL for Index STUDENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."STUDENTS_PK" ON "ADMIN"."APPLICANTS" ("ID")
  ;
--------------------------------------------------------
--  DDL for Index EXAMS_LIST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."EXAMS_LIST_PK" ON "ADMIN"."EXAMS_LIST" ("EXAM_ID")
  ;
--------------------------------------------------------
--  DDL for Index FACULTETS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ADMIN"."FACULTETS_PK" ON "ADMIN"."FACULTY_LIST" ("FACULTY_ID")
  ;
--------------------------------------------------------
--  DDL for Trigger TR_EXAM_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_EXAM_ID"
   before insert on "ADMIN"."EXAMS_LIST"
   for each row
begin
   if inserting then
      if :NEW."EXAM_ID" is null then
         select SQ_EXAM_ID.nextval into :NEW."EXAM_ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_EXAM_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_EXAM_RECORD_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_EXAM_RECORD_ID"
   before insert on "ADMIN"."EXAMINATION_RECORDS"
   for each row
begin
   if inserting then
      if :NEW."EXAM_RECORD_ID" is null then
         select EXAM_RECORD_ID.nextval into :NEW."EXAM_RECORD_ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_EXAM_RECORD_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_FACULTY_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_FACULTY_ID"
   before insert on "ADMIN"."FACULTY_LIST"
   for each row
begin
   if inserting then
      if :NEW."FACULTY_ID" is null then
         select SQ_FACULTY_ID.nextval into :NEW."FACULTY_ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_FACULTY_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_STUDENTS_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_STUDENTS_ID"
   before insert on "ADMIN"."APPLICANTS"
   for each row
begin
   if inserting then
      if :NEW."ID" is null then
         select SQ_STUDENT_ID.nextval into :NEW."ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_STUDENTS_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_STUDENTS_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_STUDENTS_ID"
   before insert on "ADMIN"."APPLICANTS"
   for each row
begin
   if inserting then
      if :NEW."ID" is null then
         select SQ_STUDENT_ID.nextval into :NEW."ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_STUDENTS_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_EXAM_RECORD_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_EXAM_RECORD_ID"
   before insert on "ADMIN"."EXAMINATION_RECORDS"
   for each row
begin
   if inserting then
      if :NEW."EXAM_RECORD_ID" is null then
         select EXAM_RECORD_ID.nextval into :NEW."EXAM_RECORD_ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_EXAM_RECORD_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_EXAM_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_EXAM_ID"
   before insert on "ADMIN"."EXAMS_LIST"
   for each row
begin
   if inserting then
      if :NEW."EXAM_ID" is null then
         select SQ_EXAM_ID.nextval into :NEW."EXAM_ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_EXAM_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_FACULTY_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ADMIN"."TR_FACULTY_ID"
   before insert on "ADMIN"."FACULTY_LIST"
   for each row
begin
   if inserting then
      if :NEW."FACULTY_ID" is null then
         select SQ_FACULTY_ID.nextval into :NEW."FACULTY_ID" from dual;
      end if;
   end if;
end;

/
ALTER TRIGGER "ADMIN"."TR_FACULTY_ID" ENABLE;
--------------------------------------------------------
--  Constraints for Table APPLICANTS
--------------------------------------------------------

  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("SCHOOL_AVG_SCORES" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("ST_EMAIL" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("ST_PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("ENROLLED" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("FIRST_NAME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" MODIFY ("LAST_NAME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."APPLICANTS" ADD CONSTRAINT "STUDENTS_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table EXAMINATION_RECORDS
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EXAMINATION_RECORDS" MODIFY ("STUDENT_ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EXAMINATION_RECORDS" MODIFY ("EXAM_ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EXAMINATION_RECORDS" MODIFY ("GRADE" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EXAMINATION_RECORDS" MODIFY ("EXAM_RECORD_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EXAMS_FACULTY
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EXAMS_FACULTY" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EXAMS_FACULTY" MODIFY ("EXAM_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EXAMS_LIST
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EXAMS_LIST" MODIFY ("EXAM_ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EXAMS_LIST" MODIFY ("EXAM_NAME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."EXAMS_LIST" ADD CONSTRAINT "EXAMS_LIST_PK" PRIMARY KEY ("EXAM_ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table FACULTY_LIST
--------------------------------------------------------

  ALTER TABLE "ADMIN"."FACULTY_LIST" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."FACULTY_LIST" MODIFY ("FACULTY_NAME" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."FACULTY_LIST" ADD CONSTRAINT "FACULTETS_PK" PRIMARY KEY ("FACULTY_ID")
  USING INDEX  ENABLE;
  ALTER TABLE "ADMIN"."FACULTY_LIST" MODIFY ("FACULTY_CAPACITY" NOT NULL ENABLE);
  ALTER TABLE "ADMIN"."FACULTY_LIST" MODIFY ("FACULTY_MIN_GRADE" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table APPLICANTS
--------------------------------------------------------

  ALTER TABLE "ADMIN"."APPLICANTS" ADD CONSTRAINT "CS_FACULTY_ID" FOREIGN KEY ("FACULTY_ID")
	  REFERENCES "ADMIN"."FACULTY_LIST" ("FACULTY_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EXAMINATION_RECORDS
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EXAMINATION_RECORDS" ADD CONSTRAINT "CS_STUDENT_ID" FOREIGN KEY ("STUDENT_ID")
	  REFERENCES "ADMIN"."APPLICANTS" ("ID") ENABLE;
  ALTER TABLE "ADMIN"."EXAMINATION_RECORDS" ADD CONSTRAINT "CS_EXAM_ID" FOREIGN KEY ("EXAM_ID")
	  REFERENCES "ADMIN"."EXAMS_LIST" ("EXAM_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EXAMS_FACULTY
--------------------------------------------------------

  ALTER TABLE "ADMIN"."EXAMS_FACULTY" ADD CONSTRAINT "EXAM_FACULTY" FOREIGN KEY ("EXAM_ID")
	  REFERENCES "ADMIN"."EXAMS_LIST" ("EXAM_ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "ADMIN"."EXAMS_FACULTY" ADD CONSTRAINT "FACULTY_EXAM" FOREIGN KEY ("FACULTY_ID")
	  REFERENCES "ADMIN"."FACULTY_LIST" ("FACULTY_ID") ENABLE;
