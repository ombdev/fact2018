#!/bin/sh

#---------------------------------------#
# Script's name : signer_sha1_rsa       # 
# Created by : Edwin Plauchu            #
# blog : https://eplauchu.wordpress.com #
#---------------------------------------#

# it creates an empty ramdom file
# within os temporal file spot
#
# param 1 --> length file name (without post fix)
# param 2 --> the post fix
# returns --> the temporal file
function create_tmp_file {
   TF="/tmp/$(cat /dev/urandom| tr -dc 'a-zA-Z0-9'| fold -w $1 |head -n 1).$2"
   touch $TF
   echo $TF
}

# Detects whether the file has one in the first place or not
#
# param 1 --> the utf-8 file to analize
# returns --> exit code of the last command run
function __has_bom() { 
   head -c3 "$1" | grep -q $'\xef\xbb\xbf'; 
}

# It makes an off Byte-order 
# mark version from a utf-8 bom file
#
# param 1 --> the utf-8 file with bom
# returns --> A temporal file which is the off bom version 
function __setup_bom_off_ver {

   TNBOM=`create_tmp_file 8 txt`

   if [ `__has_bom $1` ]; then

      awk 'NR==1{sub(/^\xef\xbb\xbf/,"")}{print}' $1 > $TNBOM
   else

      cat $1 > $TNBOM
   fi

   echo $TNBOM
}

# It signs an string within a txt file
#
# param 1 --> the key
# param 2 --> the key password
# param 3 --> input file with string to sign
# returns --> A temporal file which is the signed version of input file 
function signer_sha1_rsa {
   TRES=`create_tmp_file 8 txt`
   TNBOM=`__setup_bom_off_ver $3`
   TPEM=`create_tmp_file 32 pem`
   openssl pkcs8 -inform DER -in $1 -passin pass:$2 -out $TPEM
   openssl dgst -sha1 -sign $TPEM $TNBOM | openssl base64 -out $TRES
   rm -f $TPEM $TNBOM
   echo $TRES
}

#cont='Kcm$130624'
#llave='/home/j2eeserver/maxima/res/keys/CSD_LOS_LERMAS_KCM130624FR7_20131007_173738.key'

#signer_sha1_rsa $llave $cont /etc/services
