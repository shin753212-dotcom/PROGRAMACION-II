class Sensor:
    tipo = "Genérico"
    def __init__(self, id):
        self.id = id
        self.encendido = False
    def activar(self):
        self.encendido = True
        print("Sensor", self.id, "activado")
    def desactivar(self):
        self.encendido = False
        print("Sensor", self.id, "desactivado")
class Camara(Sensor):
    tipo = "Cámara"
    def __init__(self, id, resolucion):
        super().__init__(id)
        self.resolucion = resolucion
class SensorLuzLaser(Sensor):
    tipo = "Luz Laser"
    def __init__(self, id, alcance):
        super().__init__(id)
        self.alcance = alcance
class VehiculoAutonomo(Camara, SensorLuzLaser):
    tipo = "Vehículo Autónomo"
    def __init__(self, id, resolucion, alcance):
        # Inicializamos explícitamente la clase base para manejar la herencia múltiple
        Sensor.__init__(self, id)
        self.resolucion = resolucion
        self.alcance = alcance
class Aplicacion:
    @staticmethod
    def main():
        vehiculo = VehiculoAutonomo("VA001", "4K", 100)
        print("Tipo:", vehiculo.tipo)
        print("ID:", vehiculo.id)
        print("Resolución:", vehiculo.resolucion)
        print("Alcance:", vehiculo.alcance)
        print("Encendido:", vehiculo.encendido)
        vehiculo.activar()
        print("Encendido:", vehiculo.encendido)
        vehiculo.desactivar()
        print("Encendido:", vehiculo.encendido)
if __name__ == "__main__":
    Aplicacion.main()