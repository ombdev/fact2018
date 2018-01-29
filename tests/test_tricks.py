import sys
import os

sys.path.append(os.path.abspath('.'))

for p in sys.path:
    print(p)

from cfdiengine.misc.tricks import truncate

def test_truncate():
    """
    Test arguments with positive, negative and not only two decimal digits
    """
    assert truncate(15.22315, 3) == 15.223
    assert truncate(15.22215, 2) == 15.22
    assert truncate(33315.22215, 2) == 33315.22
    assert truncate(-1.22215, 2) == -1.22
    assert truncate(15.92215, 2) == 15.92
    assert truncate(.00215, 2) == 0.0
    assert truncate(.00215, 1) == 0.0
    assert truncate(15.22315, 3, True) == '15.223'
    assert truncate(15.22215, 2, True) == '15.22'
    assert truncate(33315.22215, 2, True) == '33315.22'
    assert truncate(-1.22215, 2, True) == '-1.22'
    assert truncate(15.92215, 2, True) == '15.92'
    assert truncate(.00215, 2, True) == '0.00'
    assert truncate(.1, 2, True) == '0.10'
    assert truncate(.4, 1, True) == '0.4'
    
