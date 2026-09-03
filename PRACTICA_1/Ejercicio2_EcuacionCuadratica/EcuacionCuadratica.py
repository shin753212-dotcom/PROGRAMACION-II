import math

class EcuacionCuadratica:
    def __init__(self, a, b, c):
        self.__a = a
        self.__b = b
        self.__c = c
    def getDiscriminante(self):
        return self.__b ** 2 - 4 * self.__a * self.__c
    def getRaiz1(self):
        if self.getDiscriminante() < 0:
            return 0
        return (-self.__b + math.sqrt(self.getDiscriminante())) / (2 * self.__a)
    def getRaiz2(self):
        if self.getDiscriminante() < 0:
            return 0
        return (-self.__b - math.sqrt(self.getDiscriminante())) / (2 * self.__a)