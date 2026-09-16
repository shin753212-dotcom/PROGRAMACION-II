import math
class AlgebraVectorial:
    def __init__(self, x=0, y=0):
        self.__x = x
        self.__y = y
    def getX(self):
        return self.__x
    def getY(self):
        return self.__y
    def productoEscalar(self, b):
        return self.__x * b.getX() + self.__y * b.getY()
    def magnitud(self):
        return math.sqrt(self.__x ** 2 + self.__y ** 2)
    def magnitud2(self):
        return self.__x ** 2 + self.__y ** 2
    def perpendicular1(self, b):
        suma = AlgebraVectorial(self.__x + b.getX(), self.__y + b.getY())
        resta = AlgebraVectorial(self.__x - b.getX(), self.__y - b.getY())
        return suma.magnitud() == resta.magnitud()
    def perpendicular2(self, b):
        resta1 = AlgebraVectorial(self.__x - b.getX(), self.__y - b.getY())
        resta2 = AlgebraVectorial(b.getX() - self.__x, b.getY() - self.__y)
        return resta1.magnitud() == resta2.magnitud()
    def perpendicular3(self, b):
        return self.productoEscalar(b) == 0
    def perpendicular4(self, b):
        suma = AlgebraVectorial(self.__x + b.getX(), self.__y + b.getY())
        return suma.magnitud2() == self.magnitud2() + b.magnitud2()
    def paralela1(self, b):
        return self.__x * b.getY() == self.__y * b.getX()
    def paralela2(self, b):
        return self.__x * b.getY() - self.__y * b.getX() == 0
    def proyeccion(self, b):
        return self.productoEscalar(b) / b.magnitud2()
    def componente(self, b):
        return self.productoEscalar(b) / b.magnitud()
    def __str__(self):
        return "(" + str(self.__x) + ", " + str(self.__y) + ")"