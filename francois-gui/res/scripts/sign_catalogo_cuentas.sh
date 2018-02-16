#!/bin/sh

. ./signer_sha1_rsa.sh


function __get_cadena_original_from_cc {
   FXSLT="../xslt/CatalogoCuentas_1_1.xslt"
   TCO=`create_tmp_file 8 txt` 
   xsltproc $FXSLT $1 -o $TCO
   echo $TCO
}


function sign_cc {
   TCO=`__get_cadena_original_from_cc $3`
   TSIGN=`signer_sha1_rsa $1 $2 $TCO`
   rm -f $TCO
   echo $TSIGN
}


KEY_DIR="$HOME/maxima/res/keys"
FILE_KEY="$KEY_DIR/CSD_LOS_LERMAS_KCM130624FR7_20131007_173738.key"
PASS_KEY='Kcm$130624'

sign_cc $FILE_KEY $PASS_KEY $1 
