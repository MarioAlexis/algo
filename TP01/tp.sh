#!/bin/bash
# tp.sh
nocolor='\033[0m'
red='\033[0;31m'
green='\033[0;32m'
orange='\033[0;33m'

#Recoit en argurment l'erreur durant la compilation du code pour le calcul du seuil
function seuilerror()
{
	echo "$1"
	case $1 in
		1)
			echo -e "${red}Aucun argument fournir. SVP faite la commande ./tp.sh --help pour connaitre les arguments de l'option -s"
			echo -e "${red}Error - Exit"
			exit
		;;
		2)
			echo -e "${red}Trop de argument fournir. SVP faite la commande ./tp.sh --help"
			echo -e "${red}Error - Exit"
			exit
			;;
		3)
			echo -e "${red}Le premier argument doit etre 1 ou 2. Faire la commande ./tp.sh --help pour plus d'information"
			echo -e "${red}Error - Exit"
			exit
		;;
		*)
		;;
	esac
}
#Fonction pour etablir le seuil dans le merge sort et dans le bucket sort
function calculseuil()
{
	choice="$1"
	nbelement="$2"
	limitmax="$3"
	pas="$4"
	echo
	cd src/
	javac MainSeuil.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Calcul du seuil -- Compilation REUSSI\n${green}***************"
	fi
	echo
	echo -e "${orange}Demarrge du calcul du seuil"
	echo -e "${nocolor}Creation du fichier calculSeuil.csv"
	java MainSeuil ${choice} ${nbelement} ${limitmax} ${pas} > ../calculSeuil.csv
	seuilerror $?
	echo -e "${green}Fichier calculSeuil.csv est pret"
	echo
}
# Help Function
function help()
{
  echo
  echo -e  "-------------------------------\n      MENU AIDE POUR TP.SH\n-------------------------------\n"
  echo
  echo "Le script doit etre appeller ce cette maniere :"
  echo 
  echo "          tp.sh [OPTIONS] -a [bucket | bucketSeuil | merge | mergeSeuil] -e path_vers_fichier_txt"
  echo
  echo "[OPTIONS] : "
  echo
  echo -e "\t-p : Affiche les nombres tries\n\t-t : Affiche le temps d\'execution\n\t-s : Execute le code pour le calcul du seuil"
  echo -e "\t     Ce dernier code va creer un fichier CSV qui explique notre choix du seuil\n\n"
}

# Parse Arguments
while [[ $# -gt 0 ]]
do
  arg="$1"

  case $arg in
    --help|-help)
	help
    	;;
    -s)
	calculseuil $2 $3 $4 $5
	;;	
    #default
    *)
    echo "NEED ARGUMENTS"
    ;;
    
  esac
  shift
done

