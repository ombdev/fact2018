from cfdiengine.misc.helperxml import HelperXml

def test_run_xslt():
    HelperXml.run_xslt('tests/sample_files/sample1.xml', 'tests/sample_files/sample1.xsl')
