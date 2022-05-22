using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Laboratory_9
{
    [XmlType("car")]
    public class Car
    {
        public string model { get; set; }
        public int year { get; set; }

        [XmlElement(ElementName="engine")]
        public Engine motor { get; set; }

        public Car() { }

        public Car(string model, Engine motor, int year)
        {
            this.model = model;
            this.year = year;
            this.motor = motor;
        }

    }
}
