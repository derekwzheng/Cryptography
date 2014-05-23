# A demo of Caesar cipher

""" The offset of ASCII characters. So we can convert numbers to English
    alphabet letters such that 0 |--> 'a', 1 |--> 'b', ..., 25 |--> 'z'. """
OFFSET = 97
""" The constant that represents the number of shifting all alphabet
    letters."""
SHIFTING = -3
""" The constant that is set to be the number of English alphabet. """
NUM_ALPHABET = 26

""" The main structure and example of Caesar Cipher procedure. """
def main():
    plainText = input("Enter the plain_text: ")
    encryption = makeEncryption(SHIFTING)
    cipherText = encryption(plainText)
    print("The cipher: " + cipherText)
    decryption = makeDecryption(SHIFTING)
    decryptedText = decryption(cipherText)
    print("The decrypted text: " + decryptedText)

""" The function makeEncryption is a higher-order function that takes in an
    integer SHIFTING and returns a encrytion function. """
def makeEncryption(shifting):
    """ The encryption function takes in a plain text string INP and SHIFTING.
    Apply Caesar cipher procedure to shifting the INP by shifting SHIFTING
    letters to the right. """
    def encryption(inp):
        inp = inp.lower()
        output = ""
        for character in inp:
            if character.isalpha(): 
                number = ord(character) - OFFSET
                # The real encryption function
                cipher = (number + shifting) % NUM_ALPHABET
                output += chr(cipher + OFFSET)
            else:
                output += character
        return output
    return encryption

""" The function makeDecription is a higher-order function that takes in an
    integer SHIFTING and returns a decription function. """
def makeDecryption(shifting):
    """The inverse function of the encryption function.
    The decryption function takes in a cipher text string INP and SHIFTING.
    Apply inverse Caesar cipher procedure to shifting the INP by shifting
    SHIFTING letters to the left. """
    def decryption(inp):
        inp = inp.lower()
        output = ""
        for character in inp:
            if character.isalpha():
                number = ord(character) - OFFSET
                plain = (number - shifting) % NUM_ALPHABET
                output += chr(plain + OFFSET)
            else:
                output += character
        return output
    return decryption


if __name__=='__main__':
    main()
