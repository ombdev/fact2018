from cfdiengine.misc.helperxml import HelperXml

def test_run_xslt():
    HelperXml.run_xslt('./file.xml', './file.xsl')
