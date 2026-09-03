from EcuacionCuadratica import EcuacionCuadratica

datos = input("Ingrese a, b, c: ").split()
a = float(datos[0])
b = float(datos[1])
c = float(datos[2])

ecuacion = EcuacionCuadratica(a, b, c)
discriminante = ecuacion.getDiscriminante()

if discriminante > 0:
    print("La ecuación tiene dos raíces {} y {}".format(ecuacion.getRaiz1(), ecuacion.getRaiz2()))
elif discriminante == 0:
    print("La ecuación tiene una raíz {}".format(ecuacion.getRaiz1()))
else:
    print("La ecuación no tiene raíces reales")