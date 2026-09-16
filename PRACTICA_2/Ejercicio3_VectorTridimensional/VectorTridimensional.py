import math
class VectorTridimensional:
    def __init__(self, a1=0, a2=0, a3=0):
        self.__a1 = a1
        self.__a2 = a2
        self.__a3 = a3
    def getA1(self):
        return self.__a1
    def getA2(self):
        return self.__a2
    def getA3(self):
        return self.__a3
    def __add__(self, o):
        return VectorTridimensional(
            self.__a1 + o.getA1(),
            self.__a2 + o.getA2(),
            self.__a3 + o.getA3()
        )
    def __sub__(self, o):
        return VectorTridimensional(
            self.__a1 - o.getA1(),
            self.__a2 - o.getA2(),
            self.__a3 - o.getA3()
        )
    def __mul__(self, r):
        return VectorTridimensional(r * self.__a1, r * self.__a2, r * self.__a3)
    __rmul__ = __mul__
    def longitud(self):
        return math.sqrt(self.__a1 ** 2 + self.__a2 ** 2 + self.__a3 ** 2)
    def normal(self):
        l = self.longitud()
        return VectorTridimensional(self.__a1 / l, self.__a2 / l, self.__a3 / l)
    def productoEscalar(self, o):
        return self.__a1 * o.getA1() + self.__a2 * o.getA2() + self.__a3 * o.getA3()
    def productoVectorial(self, o):
        x = self.__a2 * o.getA3() - self.__a3 * o.getA2()
        y = self.__a3 * o.getA1() - self.__a1 * o.getA3()
        z = self.__a1 * o.getA2() - self.__a2 * o.getA1()
        return VectorTridimensional(x, y, z)
    def __str__(self):
        return "({:.2f}, {:.2f}, {:.2f})".format(self.__a1, self.__a2, self.__a3)