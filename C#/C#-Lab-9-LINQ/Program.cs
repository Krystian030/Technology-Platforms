using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;
using System.Xml.Serialization;
using System.Xml.XPath;

namespace Laboratory_9
{
    internal class Program
    {

        public static void LinqCreate(List<Car> cars)
        {
            var newCars = from car in cars
                          where car.model == "A6"
                          select new
                          {
                              engineType = car.motor.model == "TDI" ? "diesel" : "petrol",
                              hppl = car.motor.horsePower / car.motor.displacement
                          };
            foreach (var car in newCars) 
            {
                Console.WriteLine("Engine type: {0}", car.engineType);
                Console.WriteLine("Hppl: {0}\n", car.hppl);
            }

            var newCarsGroupByEngineType = from car in newCars
                                           group car by car.engineType into groupCar
                                           select new
                                           {
                                               engineType = groupCar.Key,
                                               avg = groupCar.Average(a => a.hppl)
                                           };
            foreach (var car in newCarsGroupByEngineType)
            {
                Console.WriteLine("{0}: {1}", car.engineType, car.avg);
            }
                                           
        }                       

        public static void SerializeCars(string path, List<Car> cars)
        {
            XmlSerializer serializer = new XmlSerializer(typeof(List<Car>), new XmlRootAttribute("cars"));
            FileStream fs = new FileStream(path, FileMode.Create);
            serializer.Serialize(fs, cars);
            fs.Close();
        }
        
        public static void DeserializeCars(string path)
        {
            XmlSerializer serializer = new XmlSerializer(typeof(List<Car>), new XmlRootAttribute("cars"));
            FileStream fs = new FileStream(path, FileMode.Open);
            List<Car> cars = (List<Car>)serializer.Deserialize(fs);
            fs.Close();
            Console.WriteLine("\n=== Zdeserializowane samochody ===");
            foreach(var car in cars)
            {
                Console.WriteLine("model: {0}",car.model);
                Console.WriteLine("rok: {0}",car.year);
            }
        }

        public static void XpathExercise(string path)
        {
            XElement rootNode = XElement.Load(path);
            string xPathExpression1 = "sum(//car/engine[@model!=\"TDI\"]/horsePower) div count(//car/engine[@model!=\"TDI\"]/horsePower)";
            double avgHP = (double)rootNode.XPathEvaluate(xPathExpression1);
            Console.WriteLine("Średnia wynosi: {0}", avgHP);
            string myXPathExpression2 = "//car[not(model = following:: car/model)]";
            IEnumerable<XElement> modelsXElement = rootNode.XPathSelectElements(myXPathExpression2);

            foreach (XElement model in modelsXElement)
            {
                Console.WriteLine(model);
            }

        }

        public static void createXmlFromLinq(List<Car> myCars, string path)
        {
            IEnumerable<XElement> nodes = from car in myCars
                                          select new
                                          XElement("car", 
                                            new XElement("model",car.model),
                                            new XElement("engine", new XAttribute("model",car.motor.model),
                                                new XElement("displacement", car.motor.displacement),
                                                new XElement("horsePower", car.motor.horsePower)),
                                            new XElement("year", car.year)
                                          );

                                                //LINQ query expressions
            XElement rootNode = new XElement("cars", nodes); //create a root node to contain the query results
            rootNode.Save(path + "\\" + "CarsFromLinq.xml");
        }

        public static void createXHTMLTable(List<Car> myCars, string path) {
            XElement tmp = XElement.Load("template.html");
            IEnumerable<XElement> nodes = from car in myCars
                                          select new
                                          XElement("tr", new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid"),
                                            new XElement("td", car.model, new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid")),
                                            new XElement("td", car.motor.model, new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid")),
                                            new XElement("td", car.motor.displacement, new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid")),
                                            new XElement("td", car.motor.horsePower, new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid")),
                                            new XElement("td", car.year, new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid"))
                                          );

            //LINQ query expressions
            XElement table = new XElement("table", nodes, new XAttribute("style", "border-width: 1; border-color:Black; border-style:solid")); //create a root node to contain the query results
            tmp.Add(table);
            tmp.Save(path + "\\" + "table.html");
        }

        public static void modifyXML(List<Car> myCars, string path, string savePath)
        {
            XElement carsCollection = XElement.Load(path);
            //Console.WriteLine(carsCollection.ToString());
            //Console.WriteLine();
            var carsCollectionModify = carsCollection
                .Elements("car").Elements("engine").Elements("horsePower");

            foreach(var element in carsCollectionModify)
            {
                element.Name = "hp";
            }
            carsCollectionModify = carsCollection
                .Elements("car").Elements("year");

            foreach (var element in carsCollectionModify)
            {
                element.Parent.Element("model").Add(new XAttribute("year", element.Value));
                element.Remove();
            }
            Console.WriteLine(carsCollection.ToString());
            carsCollection.Save(savePath + "\\" + "modifyXML.xml");
        }

        static void Main(string[] args)
        {
            List<Car> myCars = new List<Car>()
            {
                new Car("E250", new Engine(1.8, 204, "CGI"), 2009),
                new Car("E350", new Engine(3.5, 292, "CGI"), 2009),
                new Car("A6", new Engine(2.5, 187, "FSI"), 2012),
                new Car("A6", new Engine(2.8, 220, "FSI"), 2012),
                new Car("A6", new Engine(3.0, 295, "TFSI"), 2012),
                new Car("A6", new Engine(2.0, 175, "TDI"), 2011),
                new Car("A6", new Engine(3.0, 309, "TDI"), 2011),
                new Car("S6", new Engine(4.0, 414, "TFSI"), 2012),
                new Car("S8", new Engine(4.0, 513, "TFSI"), 2012)
            };
            string path = Directory.GetParent(Directory.GetCurrentDirectory()).FullName;
            path = Directory.GetParent(path).FullName;
            string fileName = "CarsCollection.xml";
            string finalPath = path + "\\" + fileName;
            /*
            // Zadanie 1
            LinqCreate(myCars);

            // Zadanie 2            
            string path = Directory.GetParent(Directory.GetCurrentDirectory()).FullName;
            path = Directory.GetParent(path).FullName;
            string fileName = "CarsCollection.xml";
            string finalPath = path + "\\" + fileName;
            SerializeCars(finalPath, myCars);
            DeserializeCars(finalPath);

            // Zadanie 3
            XpathExercise(finalPath);

            // Zadanie 4
            createXmlFromLinq(myCars, path);

            // Zadanie 5
            createXHTMLTable(myCars, path);
            */
            // Zadanie 6
            modifyXML(myCars, finalPath, path);
        }

    }
}
