import random
class Juego:
    def __init__(self, numeroDeVidas):
        self.numeroDeVidas = numeroDeVidas
        self.record = 0
    def reiniciaPartida(self):
        self.numeroDeVidas = 3
    def actualizaRecord(self):
        self.record += 1
        print("Nuevo record:", self.record)
    def quitaVida(self):
        self.numeroDeVidas -= 1
        print("Te queda(n)", self.numeroDeVidas, "vida(s)")
        return self.numeroDeVidas > 0
class JuegoAdivinaNumero(Juego):
    def __init__(self, numeroDeVidas):
        super().__init__(numeroDeVidas)
        self.numeroAAdivinar = 0
    def juega(self):
        self.reiniciaPartida()
        self.numeroAAdivinar = random.randint(0, 10)

        print("\nAdivina un número entre 0 y 10")

        while True:
            numero = int(input("Introduce un número: "))

            if numero == self.numeroAAdivinar:
                print("¡Acertaste!")
                self.actualizaRecord()
                return
            if self.quitaVida():
                if numero < self.numeroAAdivinar:
                    print("El número a adivinar es MAYOR")
                else:
                    print("El número a adivinar es MENOR")
                print("Inténtalo de nuevo")
            else:
                print("¡Te quedaste sin vidas!")
                print("El número era:", self.numeroAAdivinar)
                return
class Aplicacion:
    @staticmethod
    def main():
        juego = JuegoAdivinaNumero(3)
        juego.juega()
        
Aplicacion.main()