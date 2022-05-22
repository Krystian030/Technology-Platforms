using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace PT_Lab_1
{
    static class Program
    {
        static void Main(string[] args)
        {

            string path = args[0];

            if (File.Exists(path))
            {
                Process("File", path);
            }
            else if (Directory.Exists(path))
            {
                ProcessDirectoryEx2(path,"");
               
                DirectoryInfo pathInfo = new DirectoryInfo(path);
                //Console.WriteLine("Najstarszy element: {0}", pathInfo.GetOldestItem());
                //Console.WriteLine("RAHS plik: {0}", pathInfo.RAHSattribute());
               
                Console.WriteLine();
                CollectionEx5(pathInfo);
                print(Deserialize());
            }
            else
            {
                Console.WriteLine("{0} is not a valid file or directory.", path);
            }
           
            Console.ReadLine();
        }




        public static void CollectionEx5(DirectoryInfo directoryInfo)
        {
            SortedDictionary<string,int> items = new SortedDictionary<string,int>(new Comparer());
            foreach (var item in directoryInfo.GetFiles())
            {
                items.Add(item.Name, (int)item.Length);

            }
            foreach (var item in directoryInfo.GetDirectories())
            {
                items.Add(item.Name, item.GetFiles().Length + item.GetDirectories().Length);
            }
            Serialize(items);
        }

        public static void Serialize(SortedDictionary<string, int> items)
        {
            FileStream fs = new FileStream("DataFile.dat", FileMode.Create);
            BinaryFormatter formatter = new BinaryFormatter();
            try
            {
                formatter.Serialize(fs, items);
            }
            catch (SerializationException e)
            {
                Console.WriteLine("Failed to serialize. Reason: " + e.Message);
                throw;
            }
            finally
            {
                fs.Close();
            }
        }

        public static SortedDictionary<string, int> Deserialize()
        {
            SortedDictionary<string, int> items = null;
            FileStream fs = new FileStream("DataFile.dat", FileMode.Open);
            try
            {
                BinaryFormatter formatter = new BinaryFormatter();
                items = (SortedDictionary<string, int>)formatter.Deserialize(fs);
            }
            catch (SerializationException e)
            {
                Console.WriteLine("Failed to deserialize. Reason: " + e.Message);
                throw;
            }
            finally
            {
                fs.Close();
            }
            return items;
        }

        public static void print(SortedDictionary<string, int> items)
        {
            foreach(var item in items)
            {
                Console.WriteLine("{0} -> {1}", item.Key, item.Value);
            }
        }

        public static void ProcessDirectoryEx1(string targetDirectory)
        {

            string[] directoryEntries = Directory.GetDirectories(targetDirectory);
            foreach (string path in directoryEntries)
            {
                string fileName = Path.GetFileName(path);
                Process("Directory: ", fileName);
            }

            string[] fileEntries = Directory.GetFiles(targetDirectory);
            foreach (string path in fileEntries)
            {
                string fileName = Path.GetFileName(path);
                Process("File: ", fileName);
            }

        }

        public static void ProcessDirectoryEx2(string targetDirectory, string indentation)
        {
            if (Directory.Exists(targetDirectory))
            {

                var dirEntries = Directory.GetDirectories(targetDirectory);
                var fileEntries = Directory.GetFiles(targetDirectory);

                int itemCount  = dirEntries.Length + fileEntries.Length;

                Console.WriteLine("{0} {1} ({2}) {3}", indentation, getName(targetDirectory), itemCount,(new DirectoryInfo(targetDirectory)).RAHSattribute());
                
                List<string> myList = dirEntries.ToList();
                myList.AddRange(fileEntries.ToList());

                indentation += "\t";
                foreach (string path in myList)
                {
                    ProcessDirectoryEx2(path, indentation);
                }
            }
            else
            {
                Console.WriteLine("{0} {1} {2} bajtów {3}", indentation, getName(targetDirectory),
                    (new FileInfo(targetDirectory)).Length, (new DirectoryInfo(targetDirectory)).RAHSattribute());
            }

        }

        public static DateTime GetOldestItem(this DirectoryInfo dInfo)
        {
            DateTime date = DateTime.Now;
            DateTime dateInfo;

            foreach (DirectoryInfo path in dInfo.GetDirectories())
            {
                dateInfo = path.GetOldestItem();
                if (dateInfo < date)
                {
                    date = dateInfo;
                }

            }

            foreach (FileInfo path in dInfo.GetFiles())
            {
                dateInfo = path.CreationTime;
                if (dateInfo < date)
                {
                    date = dateInfo;
                }

            }

            if(dInfo.GetFiles().Length == 0 && dInfo.GetDirectories().Length == 0)
            {
                date = dInfo.CreationTime;
            }

            return date;
        }

        public static string RAHSattribute(this FileSystemInfo sInfo)
        {
            char[] rahs= {'-','-','-','-'};
            FileAttributes fileAttributes = sInfo.Attributes;

            if((fileAttributes & FileAttributes.ReadOnly) == FileAttributes.ReadOnly) rahs[0] = 'R';
            if((fileAttributes & FileAttributes.Archive) == FileAttributes.Archive) rahs[1] = 'A';
            if((fileAttributes & FileAttributes.Hidden) == FileAttributes.Hidden) rahs[2] = 'H';
            if((fileAttributes & FileAttributes.System) == FileAttributes.System) rahs[3] = 'S';

            return new string(rahs);
        }

        public static string getName(string path)
        {
            return Path.GetFileName(path);
        }

        public static void Process(string o, string path)
        {
            Console.WriteLine("Processed {0}: {1}",o, path);
        }

    }
}
