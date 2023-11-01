# Creamos la clase libro

class libro:
    def __init__(self,titulo,autor,fecha,genero):
        self.titulo = titulo
        self.autor = autor 
        self.fecha = fecha
        self.genero = genero

# Creamos estas funciones que permiten mostrar los datos de cada libro       

    def get_titulo(self):
        return self.titulo
    
    def get_autor(self):
        return self.autor

    def get_fecha(self):
        return self.fecha

    def get_genero(self):
        return self.genero
    
# Creamos la clase biblioteca y las siguientes funciones para buscar, eliminar o mostrar cada libro    

class biblioteca:
    def __init__(self):    
        self.libros = [] 

    def agregar_libro(self, libro):
        self.libros.append(libro)  

    def buscar_libro(self, titulo):
        for libro in self.libros:
            if libro.get_titulo():
                return libro
            return None

    def eliminar_libro(self, libro):
        if libro in self.libros:
            self.libros.remove(libro)
            print("Se ha eliminado el libro")   
        else:
            print("No se encontro el libro")   

    def mostrar_libros(self):
        for libro in self.libros:
            print("Titulo:", libro.get_titulo())     
            print("Autor:", libro.get_autor())  
            print("Fecha:", libro.get_fecha())        
            print("Genero:", libro.get_genero()) 

# Ejemplo de uso

biblioteca = biblioteca()

libro1 = libro("MoviDick","Albert Tesla",1567,"Drama")
libro2 = libro("Fast and Furious 5","Vin Diesel",2011,"Accion")
libro3 = libro("Avengers","Stanley",1989,"Fantasia")
libro4 = libro("El Padrino","Al Pacino",1920,"Novela")

biblioteca.agregar_libro(libro2)
biblioteca.buscar_libro(libro4)
biblioteca.eliminar_libro(libro1)
biblioteca.mostrar_libros()    