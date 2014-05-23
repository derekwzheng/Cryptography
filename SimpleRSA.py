# Demo of RSA
# Written by Derek Zheng
# Last date modified: March 26th, 2014

from random import randint
from fractions import gcd
from extendedEuclideanAlgorithm import iter_egcd
from math import sqrt, ceil
from asciiArt import success, rsaRocks

""" The global constants for the main() function demo """
START = 5e13
END = 1e15
MESSAGE = randint(1, START * END)

""" A higher-order function takes in two large primes P and Q and returns an
    encryption function and a decryption function. """
def makeRSA(p, q):
    n = p * q
    phi = (p - 1) * (q - 1)
    while True:
        encryKey = randint(1, phi - 1)
        if (gcd(encryKey, phi) == 1):
            break
    (g, x, y) = iter_egcd(encryKey, phi)
    decryKey = x % phi
    
    """ The encryption function takes a msg  0<= M < n and returns the
        ciphertext. """
    def encryption(m):
        return pow(m, encryKey, n)
    
    """ The decryption function takes in the cipertext C and returns the
        plaintext message. """
    def decryption(c):
        return pow(c, decryKey, n)
    
    return (encryption, decryption) 

""" Returns two distinct random primes START<= prime <= END. If it fails,
    return None. """
def findTwoLargePrimes(start, end):
    checked = set()
    result = []
    for counter in range(2):
        while True:
            num = randint(start, end)
            if num not in checked and isPrime(num):
                result.append(num)
                checked.add(num)
                break
            checked.add(num)
    return tuple(result)
                    
""" Return true iff NUM is a prime. """
def isPrime(num):
    for j in range(2, ceil(sqrt(num))):
        if (num % j) == 0:
            return False
    return True

""" An example of using the RSA algorithm """
def main():
    (p, q) = findTwoLargePrimes(START, END)
    (eFunction, dFunction) = makeRSA(p, q)
    ciphertext = eFunction(MESSAGE)
    print("Plaintext: " + str(MESSAGE))
    print("Ciphertext: " + str(ciphertext))
    if dFunction(ciphertext) == MESSAGE:
        print("***** Test passed! *****")
        success()
        rsaRocks()
    else:
        print("***** Test failed. *****")
