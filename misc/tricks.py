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
    
