#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Region
#------------------------------------------------------------

CREATE TABLE Region(
        id_region  Int  Auto_increment  NOT NULL ,
        nom_region Varchar (50) NOT NULL
	,CONSTRAINT Region_PK PRIMARY KEY (id_region)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Apprenant
#------------------------------------------------------------

CREATE TABLE Apprenant(
        id_apprenant  Int  Auto_increment  NOT NULL ,
        nom           Varchar (50) NOT NULL ,
        prenom        Varchar (50) NOT NULL ,
        dateNaissance Date NULL ,
        email         Varchar (50) NOT NULL ,
        photo         Varchar (50) NULL ,
        id_region     Int NOT NULL
	,CONSTRAINT Apprenant_PK PRIMARY KEY (id_apprenant)

	,CONSTRAINT Apprenant_Region_FK FOREIGN KEY (id_region) REFERENCES Region(id_region)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Activite
#------------------------------------------------------------

CREATE TABLE Activite(
        id_activite   Int  Auto_increment  NOT NULL ,
        code_activite Char (3) NOT NULL ,
        nom_activite  Varchar (50) NOT NULL
	,CONSTRAINT Activite_PK PRIMARY KEY (id_activite)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: peutAvoir
#------------------------------------------------------------

CREATE TABLE peutAvoir(
        id_activite  Int NOT NULL ,
        id_apprenant Int NOT NULL
	,CONSTRAINT peutAvoir_PK PRIMARY KEY (id_activite,id_apprenant)

	,CONSTRAINT peutAvoir_Activite_FK FOREIGN KEY (id_activite) REFERENCES Activite(id_activite)
	,CONSTRAINT peutAvoir_Apprenant0_FK FOREIGN KEY (id_apprenant) REFERENCES Apprenant(id_apprenant)
)ENGINE=InnoDB;
