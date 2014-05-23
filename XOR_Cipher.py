import random
import string

OFFSET = 97

""" A demo for the XOR Cipher procedure. """
def main():
    message = input("Enter the message: ")
    print("message: " + message)
    key = genkey(len(message))
    print("key: " + key)
    cipherText = xorStrings(message, key)
    print("cipherText: " + numRepresentation(cipherText))
    print("decrypted: " + xorStrings(cipherText,key))

""" Generate random key of length LENGTH """
def genkey(length):
    key = ""
    for i in range(length):
        key += random.choice(string.ascii_lowercase)
    return key

"""xor two strings S and T together. """
def xorStrings(s,t):
    cipher = ""
    zipped = zip(s, t)
    for x, y in zipped:
        cipher += chr(((ord(x) - OFFSET) ^ (ord(y) - OFFSET)) + OFFSET)
    return cipher

""" Turn a string STRING into a string of number of each character in
    STRING. """
def numRepresentation(string):
    numRepr = ""
    for char in string:
        numRepr += str(ord(char)) + ' '
    return numRepr

if __name__ == "__main__":
    main()
