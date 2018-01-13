def suma(x):
    print("hola, lider")
    return x + 1

def test_suma():
    assert suma(6) == 7

def truncate(f, n, strmode=False):
    """
    Truncates/pads a float f to n decimal places without rounding
    """
    s = '{}'.format(f)
    if 'e' in s or 'E' in s:
        return '{0:.{1}f}'.format(f, n)
    i, p, d = s.partition('.')
    result = '.'.join([i, (d + '0' * n)[:n]])
    return result if strmode else float(result)

def test_truncate():
    assert truncate(15.22315, 3) == 15.223
    assert truncate(15.22215, 2) == 15.22
    assert truncate(33315.22215, 2) == 33315.22
    assert truncate(-1.22215, 2) == -1.22
    assert truncate(15.92215, 2) == 15.92
    assert truncate(.00215, 2) == 0.0
    assert truncate(15.22315, 3, True) == '15.223'
    assert truncate(15.22215, 2, True) == '15.22'
    assert truncate(33315.22215, 2, True) == '33315.22'
    assert truncate(-1.22215, 2, True) == '-1.22'
    assert truncate(15.92215, 2, True) == '15.92'
    assert truncate(.00215, 2, True) == '0.00'
