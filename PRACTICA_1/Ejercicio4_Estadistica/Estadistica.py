class Estadistica:
    def __init__(self, valores):
        self.__valores = valores
    def promedio(self):
        return sum(self.__valores) / len(self.__valores)
    def desviacion(self):
        media = self.promedio()
        sumaCuadrados = sum((v - media) ** 2 for v in self.__valores)
        return (sumaCuadrados / (len(self.__valores) - 1)) ** 0.5

datos = input("Ingrese 10 números: ").split()
valores = [float(x) for x in datos]
est = Estadistica(valores)

print("El promedio es {:.2f}".format(est.promedio()))
print("La desviación estandard es {:.5f}".format(est.desviacion()))