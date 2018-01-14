def test_format_currency():
    """
    to be more specific
    """
    assert HelperStr.format_currency('2555555555.59') == '2,555,555,555.59'
    assert HelperStr.format_currency('5.59') == '5.59'
    assert HelperStr.format_currency('1234.59') == '1,234.59'
    assert HelperStr.format_currency('0.00') == '0.00'
    #assert HelperStr.format_currency('-2555555555.59') == '-2,555,555,555.59'
    #assert HelperStr.format_currency('-9.99') == '-9.99'
    #assert HelperStr.format_currency('-.01') == '-0.01'
    
