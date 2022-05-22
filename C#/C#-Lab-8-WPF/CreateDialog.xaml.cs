using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace WPF_Laboratorium_8
{
    /// <summary>
    /// Interaction logic for CreateDialog.xaml
    /// </summary>
    public partial class CreateDialog : Window
    {

        string path;
        bool succeed = false;

        public CreateDialog(string path)
        {
            this.path = path;
            InitializeComponent();
        }
        private void ok_Click(object sender, RoutedEventArgs e)
        {
            if(!(bool)file.IsChecked && !(bool)directory.IsChecked)
            {
                MessageBox.Show("Wybierz opcje file/directory", "Warning", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            else if((bool)file.IsChecked && !Regex.IsMatch(name.Text, "^[a-zA-Z0-9_~-]{1,8}\\.(txt|php|html)$"))
            {
                MessageBox.Show("Zła nazwa dla pliku", "Warning", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            else 
            {
                string fileName = name.Text;
                path = path + "\\" + fileName;

                // Create attributes
                FileAttributes fileAttributes = FileAttributes.Normal;
                if((bool)readOnly.IsChecked) fileAttributes |= FileAttributes.ReadOnly;
                if((bool)archive.IsChecked) fileAttributes |= FileAttributes.Archive;
                if ((bool)hidden.IsChecked) fileAttributes |= FileAttributes.Hidden;
                if((bool)systemBtn.IsChecked) fileAttributes |= FileAttributes.System;

                if ((bool)directory.IsChecked) Directory.CreateDirectory(path);
                else File.Create(path);

                // Set attribute
                File.SetAttributes(path, fileAttributes);
                this.succeed = true;
                Close();
            }
        }

        public string get_path()
        {
            return path;
        }

        public bool get_succed()
        {
            return succeed;
        }

        private void cancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}
