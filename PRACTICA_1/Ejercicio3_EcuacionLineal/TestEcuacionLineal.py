from EcuacionLineal import EcuacionLineal
datos = input("Ingrese a, b, c, d, e, f: ").split()
a = float(datos[0])
b = float(datos[1])
c = float(datos[2])
d = float(datos[3])
e = float(datos[4])
f = float(datos[5])

ecuacion = EcuacionLineal(a, b, c, d, e, f)

if ecuacion.tieneSolucion():
    print("x = {}, y = {}".format(ecuacion.getX(), ecuacion.getY()))
else:
    print("La ecuación no tiene solución")