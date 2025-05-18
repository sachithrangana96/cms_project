/*
 Navicat Premium Dump SQL

 Source Server         : Local
 Source Server Type    : PostgreSQL
 Source Server Version : 170005 (170005)
 Source Host           : localhost:5432
 Source Catalog        : customer_pt
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 170005 (170005)
 File Encoding         : 65001

 Date: 18/05/2025 21:22:29
*/


-- ----------------------------
-- Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."hibernate_sequence";
CREATE SEQUENCE "public"."hibernate_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."hibernate_sequence" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for mobile_number_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mobile_number_id_seq";
CREATE SEQUENCE "public"."mobile_number_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "public"."mobile_number_id_seq" OWNER TO "postgres";

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS "public"."address";
CREATE TABLE "public"."address" (
  "id" int8 NOT NULL,
  "address_line1" varchar(255) COLLATE "pg_catalog"."default",
  "address_line2" varchar(255) COLLATE "pg_catalog"."default",
  "city" varchar(255) COLLATE "pg_catalog"."default",
  "country" varchar(255) COLLATE "pg_catalog"."default",
  "customer_id" int8
)
;
ALTER TABLE "public"."address" OWNER TO "postgres";

-- ----------------------------
-- Records of address
-- ----------------------------
BEGIN;
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (2, '123 Main Street', 'Apt 4B', 'Colombo', 'Sri Lanka', 1);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (3, '456 Second Street', 'Floor 2', 'Kandy', 'Sri Lanka', 1);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (6, '400 Second Street', 'Floor 2', 'Rathnapura', 'Sri Lanka', 4);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (7, '100 Main Street', 'Apto', 'galle', 'Sri Lanka', 4);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (5, '123 Main Street beautiful', 'Apt 4B', 'Kagalle', 'Sri Lanka', 4);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (9, 'lin1', 'La 4B', 'Rabukkana', 'Sri Lanka', 8);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (10, 'Line2', 'Floor 2', 'kalawana', 'Sri Lanka', 8);
INSERT INTO "public"."address" ("id", "address_line1", "address_line2", "city", "country", "customer_id") VALUES (12, 'tw1', 'tw2', 'lio', 'Sri Lanka', 11);
COMMIT;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."customer";
CREATE TABLE "public"."customer" (
  "id" int8 NOT NULL,
  "date_of_birth" timestamp(6) NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "nic" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
ALTER TABLE "public"."customer" OWNER TO "postgres";

-- ----------------------------
-- Records of customer
-- ----------------------------
BEGIN;
INSERT INTO "public"."customer" ("id", "date_of_birth", "name", "nic") VALUES (1, '1990-01-01 05:30:00', 'John Doe', '991234567V');
INSERT INTO "public"."customer" ("id", "date_of_birth", "name", "nic") VALUES (4, '1990-01-01 05:30:00', 'hone Doe', '985734567V');
INSERT INTO "public"."customer" ("id", "date_of_birth", "name", "nic") VALUES (8, '1954-01-01 05:30:00', 'salman Doe', '954875855');
INSERT INTO "public"."customer" ("id", "date_of_birth", "name", "nic") VALUES (11, '2025-05-18 05:30:00', 'ji df', '878825555');
COMMIT;

-- ----------------------------
-- Table structure for customer_family_member
-- ----------------------------
DROP TABLE IF EXISTS "public"."customer_family_member";
CREATE TABLE "public"."customer_family_member" (
  "customer_id" int8 NOT NULL,
  "family_member_id" int8 NOT NULL
)
;
ALTER TABLE "public"."customer_family_member" OWNER TO "postgres";

-- ----------------------------
-- Records of customer_family_member
-- ----------------------------
BEGIN;
INSERT INTO "public"."customer_family_member" ("customer_id", "family_member_id") VALUES (11, 1);
INSERT INTO "public"."customer_family_member" ("customer_id", "family_member_id") VALUES (8, 4);
COMMIT;

-- ----------------------------
-- Table structure for mobile_number
-- ----------------------------
DROP TABLE IF EXISTS "public"."mobile_number";
CREATE TABLE "public"."mobile_number" (
  "id" int8 NOT NULL DEFAULT nextval('mobile_number_id_seq'::regclass),
  "number" varchar(255) COLLATE "pg_catalog"."default",
  "customer_id" int8
)
;
ALTER TABLE "public"."mobile_number" OWNER TO "postgres";

-- ----------------------------
-- Records of mobile_number
-- ----------------------------
BEGIN;
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (1, '0771234567', 1);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (2, '0712345678', 1);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (4, '0700457678', 4);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (5, '0700400000', 4);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (3, '0761004560', 4);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (6, '0768521477', 8);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (7, '0700000008', 8);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (8, '541561487489', 11);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (9, '87877848788', 11);
INSERT INTO "public"."mobile_number" ("id", "number", "customer_id") VALUES (10, '87789823589', 11);
COMMIT;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."hibernate_sequence"', 12, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."mobile_number_id_seq"
OWNED BY "public"."mobile_number"."id";
SELECT setval('"public"."mobile_number_id_seq"', 10, true);

-- ----------------------------
-- Primary Key structure for table address
-- ----------------------------
ALTER TABLE "public"."address" ADD CONSTRAINT "address_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table customer
-- ----------------------------
ALTER TABLE "public"."customer" ADD CONSTRAINT "uk_9st6x9trhf0s27g0vgpcaeu3m" UNIQUE ("nic");

-- ----------------------------
-- Primary Key structure for table customer
-- ----------------------------
ALTER TABLE "public"."customer" ADD CONSTRAINT "customer_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mobile_number
-- ----------------------------
ALTER TABLE "public"."mobile_number" ADD CONSTRAINT "mobile_number_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table address
-- ----------------------------
ALTER TABLE "public"."address" ADD CONSTRAINT "fk93c3js0e22ll1xlu21nvrhqgg" FOREIGN KEY ("customer_id") REFERENCES "public"."customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table customer_family_member
-- ----------------------------
ALTER TABLE "public"."customer_family_member" ADD CONSTRAINT "fk2op4y4m1ipp7u5vlwnugg4nws" FOREIGN KEY ("customer_id") REFERENCES "public"."customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."customer_family_member" ADD CONSTRAINT "fkksrs43gh0xjnehktme8q48bs8" FOREIGN KEY ("family_member_id") REFERENCES "public"."customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table mobile_number
-- ----------------------------
ALTER TABLE "public"."mobile_number" ADD CONSTRAINT "fkcr6qudm2o3d9n7wtf4jo61rpb" FOREIGN KEY ("customer_id") REFERENCES "public"."customer" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
