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
    def validaNumero(self, numero):
        return 0 <= numero <= 10
    def juega(self):
        self.reiniciaPartida()
        self.numeroAAdivinar = random.randint(0, 10)

        print("\nAdivina un número entre 0 y 10")

        while True:
            numero = int(input("Introduce un número: "))

            if not self.validaNumero(numero):
                print("Número inválido. Debe estar entre 0 y 10.")
                continue
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
class JuegoAdivinaPar(JuegoAdivinaNumero):
    def validaNumero(self, numero):
        if numero < 0 or numero > 10:
            print("El número debe estar entre 0 y 10.")
            return False
        if numero % 2 != 0:
            print("Error: debes ingresar un número PAR.")
            return False
        return True
class JuegoAdivinaImpar(JuegoAdivinaNumero):
    def validaNumero(self, numero):
        if numero < 0 or numero > 10:
            print("El número debe estar entre 0 y 10.")
            return False
        if numero % 2 == 0:
            print("Error: debes ingresar un número IMPAR.")
            return False
        return True
class Aplicacion:
    @staticmethod
    def main():
        juego1 = JuegoAdivinaNumero(3)
        juego2 = JuegoAdivinaPar(3)
        juego3 = JuegoAdivinaImpar(3)

        print("\n JUEGO NORMAL")
        juego1.juega()

        print("\n JUEGO DE PARES")
        juego2.juega()

        print("\n  JUEGO DE IMPARES")
        juego3.juega()

Aplicacion.main()