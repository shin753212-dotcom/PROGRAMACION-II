from VectorTridimensional import VectorTridimensional

a = VectorTridimensional(1, 2, 3)
b = VectorTridimensional(4, 5, 6)

print("a =", a)
print("b =", b)
print("a + b =", a + b)
print("2 * a =", a * 2)
print("a . b =", a.productoEscalar(b))
print("a x b =", a.productoVectorial(b))
print("normal de a =", a.normal())