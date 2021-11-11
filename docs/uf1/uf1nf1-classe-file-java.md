# La classe File de Java
La classe `File` de Java representa els fitxers i els noms de ruta del directori de manera abstracta. Aquesta classe s'utilitza per a la creació de fitxers i directoris, cerca de fitxers, eliminació de fitxers, etc.

L’objecte File representa el fitxer o directori real del disc.

A continuació es mostra la llista de constructors per crear un objecte `File`.

## Constructors de la classe File

### File(File parent, String child)
This constructor creates a new File instance from a parent abstract pathname and a child pathname string.

### File(String pathname)
This constructor creates a new File instance by converting the given pathname string into an abstract pathname.

### File(String parent, String child)
This constructor creates a new File instance from a parent pathname string and a child pathname string.

### File(URI uri)
This constructor creates a new File instance by converting the given file URI into an abstract pathname.

## Mètodes per manipular objectes de la classe File

###	public String getName()
Returns the name of the file or directory denoted by this abstract pathname.

### public String getParent()
Returns the pathname string of this abstract pathname's parent, or null if this pathname does not name a parent directory.

### public File getParentFile()
Returns the abstract pathname of this abstract pathname's parent, or null if this pathname does not name a parent directory.

### public String getPath()
Converts this abstract pathname into a pathname string.

### public boolean isAbsolute()
Tests whether this abstract pathname is absolute. Returns true if this abstract pathname is absolute, false otherwise.

### public String getAbsolutePath()
Returns the absolute pathname string of this abstract pathname.

### public boolean canRead()
Tests whether the application can read the file denoted by this abstract pathname. Returns true if and only if the file specified by this abstract pathname exists and can be read by the application; false otherwise.

### public boolean canWrite()
Tests whether the application can modify to the file denoted by this abstract pathname. Returns true if and only if the file system actually contains a file denoted by this abstract pathname and the application is allowed to write to the file; false otherwise.

### public boolean exists()
Tests whether the file or directory denoted by this abstract pathname exists. Returns true if and only if the file or directory denoted by this abstract pathname exists; false otherwise.

### public boolean isDirectory()
Tests whether the file denoted by this abstract pathname is a directory. Returns true if and only if the file denoted by this abstract pathname exists and is a directory; false otherwise.

### public boolean isFile()
Tests whether the file denoted by this abstract pathname is a normal file. A file is normal if it is not a directory and, in addition, satisfies other system-dependent criteria. Any non-directory file created by a Java application is guaranteed to be a normal file. Returns true if and only if the file denoted by this abstract pathname exists and is a normal file; false otherwise.

### public long lastModified()
Returns the time that the file denoted by this abstract pathname was last modified. Returns a long value representing the time the file was last modified, measured in milliseconds since the epoch (00:00:00 GMT, January 1, 1970), or 0L if the file does not exist or if an I/O error occurs.

### public long length()
Returns the length of the file denoted by this abstract pathname. The return value is unspecified if this pathname denotes a directory.

### public boolean createNewFile() throws IOException
Atomically creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist. Returns true if the named file does not exist and was successfully created; false if the named file already exists.

### public boolean delete()
Deletes the file or directory denoted by this abstract pathname. If this pathname denotes a directory, then the directory must be empty in order to be deleted. Returns true if and only if the file or directory is successfully deleted; false otherwise.

### public void deleteOnExit()
Requests that the file or directory denoted by this abstract pathname be deleted when the virtual machine terminates.

### public String[] list()
Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.

### public String[] list(FilenameFilter filter)
Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.

### public File[] listFiles()
Returns an array of abstract pathnames denoting the files in the directory denoted by this abstract pathname.

### public File[] listFiles(FileFilter filter)
Returns an array of abstract pathnames denoting the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.

### public boolean mkdir()
Creates the directory named by this abstract pathname. Returns true if and only if the directory was created; false otherwise.

### public boolean mkdirs()
Creates the directory named by this abstract pathname, including any necessary but nonexistent parent directories. Returns true if and only if the directory was created, along with all necessary parent directories; false otherwise.

### public boolean renameTo(File dest)
Renames the file denoted by this abstract pathname. Returns true if and only if the renaming succeeded; false otherwise.

### public boolean setLastModified(long time)
Sets the last-modified time of the file or directory named by this abstract pathname. Returns true if and only if the operation succeeded; false otherwise.

### public boolean setReadOnly()
Marks the file or directory named by this abstract pathname so that only read operations are allowed. Returns true if and only if the operation succeeded; false otherwise.

### public static File createTempFile(String prefix, String suffix, File directory) throws IOException
Creates a new empty file in the specified directory, using the given prefix and suffix strings to generate its name. Returns an abstract pathname denoting a newly-created empty file.

### public static File createTempFile(String prefix, String suffix) throws IOException
Creates an empty file in the default temporary-file directory, using the given prefix and suffix to generate its name. Invoking this method is equivalent to invoking createTempFile(prefix, suffix, null). Returns abstract pathname denoting a newly-created empty file.

### public int compareTo(File pathname)
Compares two abstract pathnames lexicographically. Returns zero if the argument is equal to this abstract pathname, a value less than zero if this abstract pathname is lexicographically less than the argument, or a value greater than zero if this abstract pathname is lexicographically greater than the argument.

### public int compareTo(Object o)
Compares this abstract pathname to another object. Returns zero if the argument is equal to this abstract pathname, a value less than zero if this abstract pathname is lexicographically less than the argument, or a value greater than zero if this abstract pathname is lexicographically greater than the argument.

### public boolean equals(Object obj)
Tests this abstract pathname for equality with the given object. Returns true if and only if the argument is not null and is an abstract pathname that denotes the same file or directory as this abstract pathname.

### public String toString()
Returns the pathname string of this abstract pathname. This is just the string returned by the getPath() method.
