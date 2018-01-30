from cfdiengine.misc.helperxml import HelperXml

def test_run_xslt():
    results = HelperXml.run_xslt('tests/sample_files/sample1.xml', 'tests/sample_files/sample1.xsl')
    print(results)
    
