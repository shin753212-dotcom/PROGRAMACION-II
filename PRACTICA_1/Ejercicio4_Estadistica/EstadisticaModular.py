def promedio(valores):
    return sum(valores) / len(valores)
def desviacion(valores):
    media = promedio(valores)
    sumaCuadrados = sum((v - media) ** 2 for v in valores)
    return (sumaCuadrados / (len(valores) - 1)) ** 0.5

datos = input("Ingrese 10 números: ").split()
valores = [float(x) for x in datos]

print("El promedio es {:.2f}".format(promedio(valores)))
print("La desviación estandard es {:.5f}".format(desviacion(valores)))