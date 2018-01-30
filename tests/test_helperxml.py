from cfdiengine.misc.helperxml import HelperXml

def test_run_xslt():
    HelperXml.run_xslt('./sample_files/sample1.xml', './sample_files/sample1.xsl')
