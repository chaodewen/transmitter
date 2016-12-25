USE mdsp;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Assay;
DROP TABLE IF EXISTS AssayItem;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS DrugItem;
DROP TABLE IF EXISTS SingleDrugItem;

CREATE TABLE User
(
  id       INT              AUTO_INCREMENT,
  name     VARCHAR(40) NULL,
  age      INT         NULL,
  idcard   VARCHAR(40) NULL,
  password VARCHAR(40) NULL,
  phone    VARCHAR(40) NULL,
  email    VARCHAR(40) NULL,
  created  TIMESTAMP   NULL DEFAULT current_timestamp,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE Assay
(
  id           INT AUTO_INCREMENT,
  userId       INT,
  event        VARCHAR(40) NULL,
  patient      VARCHAR(40) NULL,
  sample       VARCHAR(40) NULL,
  gender       INT         NULL,
  department   VARCHAR(40) NULL,
  age          INT         NULL,
  demander     VARCHAR(40) NULL,
  coroner      VARCHAR(40) NULL,
  checker      VARCHAR(40) NULL,
  note         VARCHAR(40) NULL,
  startedDate  DATE        NULL,
  finishedDate DATE        NULL,
  PRIMARY KEY (id),
  CONSTRAINT assay_to_user FOREIGN KEY (userId) REFERENCES User (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE AssayItem (
  id        INT AUTO_INCREMENT,
  assayId   INT,
  name      VARCHAR(40) NULL,
  code      VARCHAR(40),
  result    VARCHAR(40) NULL,
  hint      VARCHAR(40) NULL,
  reference VARCHAR(40) NULL,
  unit      VARCHAR(40) NULL,
  PRIMARY KEY (id),
  CONSTRAINT assayItem_to_assay FOREIGN KEY (assayId) REFERENCES Assay (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Recipe
(
  id          INT AUTO_INCREMENT,
  userId      INT,
  createdDate DATE        NULL,
  name        VARCHAR(40) NULL,
  gender      INT         NULL,
  age         INT         NULL,
  recordId    VARCHAR(40) NULL,
  address     VARCHAR(40) NULL,
  diagnosis   VARCHAR(40) NULL,
  doctor      VARCHAR(40) NULL,
  charge      FLOAT(6, 2) NULL,
  checker     VARCHAR(40) NULL,
  PRIMARY KEY (id),
  CONSTRAINT recipe_to_user FOREIGN KEY (userId) REFERENCES User (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

# DROP TRIGGER IF EXISTS update_recipe;
# CREATE TRIGGER update_recipe AFTER INSERT ON Recipe FOR EACH ROW BEGIN
#   UPDATE Recipe, User
#   SET new.name = User.name AND new.age = User.age
#   WHERE new.userId = User.id;
# END;

CREATE TABLE DrugItem
(
  id             INT AUTO_INCREMENT,
  recipeId       INT,
  dosageUnit     VARCHAR(40) NULL,
  dosageQuantity INT         NULL,
  timePerDay     INT         NULL,
  note           VARCHAR(40) NULL,
  PRIMARY KEY (id),
  CONSTRAINT drugItem_to_recipe FOREIGN KEY (recipeId) REFERENCES Recipe (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE SingleDrugItem
(
  id               INT AUTO_INCREMENT,
  drugItemId       INT,
  name             VARCHAR(40) NULL,
  purchaseUnit     VARCHAR(40) NULL,
  purchaseQuantity FLOAT(6, 2) NULL,
  PRIMARY KEY (id),
  CONSTRAINT singleDrugItem_to_drugItem FOREIGN KEY (drugItemId) REFERENCES DrugItem (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

SET FOREIGN_KEY_CHECKS = 1;

SHOW TABLE STATUS;