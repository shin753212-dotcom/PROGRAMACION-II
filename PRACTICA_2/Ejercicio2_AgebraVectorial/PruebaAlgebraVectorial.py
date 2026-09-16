from AlgebraVectorial import AlgebraVectorial

a = AlgebraVectorial(3, 4)
b = AlgebraVectorial(-4, 3)
c = AlgebraVectorial(6, 8)

print("a:", a)
print("b:", b)
print("Perpendicular1:", a.perpendicular1(b))
print("Perpendicular2:", a.perpendicular2(b))
print("Perpendicular3:", a.perpendicular3(b))
print("Perpendicular4:", a.perpendicular4(b))
print("Paralela1:", a.paralela1(c))
print("Paralela2:", a.paralela2(c))
print("Proyeccion:", a.proyeccion(b))
print("Componente:", a.componente(b))