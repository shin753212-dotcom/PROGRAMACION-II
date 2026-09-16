import math
class MiPunto:
    def __init__(self, x=0, y=0):
        self.__x = x
        self.__y = y
    def getX(self):
        return self.__x
    def getY(self):
        return self.__y
    def distancia(self, p):
        return math.sqrt((self.__x - p.getX()) ** 2 + (self.__y - p.getY()) ** 2)
    def distanciaXY(self, x, y):
        return math.sqrt((self.__x - x) ** 2 + (self.__y - y) ** 2)
    def __str__(self):
        return "(" + str(self.__x) + ", " + str(self.__y) + ")"
    
p1 = MiPunto()
p2 = MiPunto(10, 30.5)

print("Punto 1:", p1)
print("Punto 2:", p2)
print("Distancia:", p1.distancia(p2))
