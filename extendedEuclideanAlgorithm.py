# An implementation of Extended Euclidean algorithm in Python

""" The function takes positive integers a and b, and return a triple
    (g, x, y) such that ax + by = g = gcd(a, b).
    An iterative implementation of Extended Euclidean Algorithm """
def rec_egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)
    
""" The function takes positive integers a and b, and return a triple
    (g, x, y) such that ax + by = g = gcd(a, b).
    An iterative implementation of Extended Euclidean Algorithm """
def iter_egcd(a, b):
    x,y, u,v = 0,1, 1,0
    while a != 0:
        q, r = b//a, b%a
        m, n = x-u*q, y-v*q
        b,a, x,y, u,v = a,r, u,v, m,n
    gcd = b
    return gcd, x, y
