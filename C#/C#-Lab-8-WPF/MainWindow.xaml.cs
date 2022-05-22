using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Forms;
using System.Diagnostics;
using System.IO;

namespace WPF_Laboratorium_8
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        DirectoryInfo directory;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Exit(object sender, RoutedEventArgs e)
        {
            System.Windows.Application.Current.Shutdown();
        }

        private void Open(object sender, RoutedEventArgs e)
        {
            var dlg = new FolderBrowserDialog() { Description = "Select directory to open" };
            var result = dlg.ShowDialog();
            if (result == System.Windows.Forms.DialogResult.OK)
            {
                directory = new DirectoryInfo(dlg.SelectedPath);
                TreeFileView();
            }
            Trace.WriteLine(directory.ToString());
        }

        public TreeViewItem AddElement(TreeViewItem root, DirectoryInfo file)
        {
            var item = new TreeViewItem
            {
                Header = file.Name,
                Tag = file.FullName
            };
            if(file.FullName != directory.FullName)
                root.Items.Add(item);

            return item;
        }

        public void ProcessDirectory(string targetDirectory, TreeViewItem root)
        {
            if (Directory.Exists(targetDirectory))
            {
                var dirEntries = Directory.GetDirectories(targetDirectory);
                var fileEntries = Directory.GetFiles(targetDirectory);
                List<string> myList = dirEntries.ToList();
                myList.AddRange(fileEntries.ToList());

                var item = AddElement(root, new DirectoryInfo(targetDirectory));

                item.ContextMenu = new System.Windows.Controls.ContextMenu();
                CreateOption(item, "Delete", DeleteItem);
                CreateOption(item, "Create", CreateItem);

                foreach (string path in myList)
                {
                    if(targetDirectory == directory.FullName)
                        ProcessDirectory(path, root);
                    else
                        ProcessDirectory(path, item);
                }
            }
            else
            {
                var item = AddElement(root, new DirectoryInfo(targetDirectory));

                item.ContextMenu = new System.Windows.Controls.ContextMenu();
                CreateOption(item, "Delete", DeleteItem);
                CreateOption(item, "Open", OpenItem);
            }
           
        }

        private void CreateOption(TreeViewItem item, string optionName, Action<object, RoutedEventArgs> methodName)
        {

            var menuItem = new System.Windows.Controls.MenuItem { Header = optionName };
            menuItem.Click += new RoutedEventHandler(methodName);
            item.ContextMenu.Items.Add(menuItem);
            item.Selected += new RoutedEventHandler(status_bar);
        }


        private void TreeFileView()
        {
            var root = new TreeViewItem
            {
                Header = directory.Name,
                Tag = directory.FullName
            };

            root.ContextMenu = new System.Windows.Controls.ContextMenu();
            CreateOption(root, "Delete", DeleteItem);
            CreateOption(root, "Create", CreateItem);

            view.Items.Add(root);
            ProcessDirectory(directory.FullName, root);

        }

        private void DeleteItem(object sender, RoutedEventArgs e)
        {
            TreeViewItem selectedItem = (TreeViewItem)view.SelectedItem;
            string dir = (string)selectedItem.Tag;

            DirectoryInfo pathInfo = new DirectoryInfo(dir);
        
            File.SetAttributes(pathInfo.FullName, FileAttributes.Normal);
            if(Directory.Exists(dir))
            {
                DeleteDir(pathInfo.FullName);
            }
            else
            {
                File.Delete(dir);
            }
            if ((TreeViewItem)view.Items[0] == selectedItem)
                view.Items.Clear();
            else
            {
                var root = (TreeViewItem)selectedItem.Parent;
                root.Items.Remove(selectedItem);
            }
            Trace.WriteLine("Delete");
        }

        private void DeleteDir(string targetDirectory)
        {
            if (Directory.Exists(targetDirectory))
            {
                var dirEntries = Directory.GetDirectories(targetDirectory);
                var fileEntries = Directory.GetFiles(targetDirectory);
                List<string> myList = dirEntries.ToList();
                myList.AddRange(fileEntries.ToList());


                foreach (string path in myList)
                {
                    DeleteDir(path);
                }
                Directory.Delete(targetDirectory);
            }
            else
            {
                File.SetAttributes(targetDirectory, FileAttributes.Normal);
                File.Delete(targetDirectory);
            }
        }

        private void CreateItem(object sender, RoutedEventArgs e)
        {
            TreeViewItem selectedItem = (TreeViewItem)view.SelectedItem;
            if (selectedItem != null)
            {
                CreateDialog createDialog = new CreateDialog((string)selectedItem.Tag);
                createDialog.ShowDialog();

                if (createDialog.get_succed())
                {
                    if (Directory.Exists(createDialog.get_path()))
                    {
                        var item = this.AddElement(selectedItem, new DirectoryInfo(createDialog.get_path()));
                        item.ContextMenu = new System.Windows.Controls.ContextMenu();

                        CreateOption(item, "Delete", DeleteItem);
                        CreateOption(item, "Create", CreateItem);
                    }
                    else if (File.Exists(createDialog.get_path()))
                    {
                        var item = this.AddElement(selectedItem, new DirectoryInfo(createDialog.get_path()));

                        item.ContextMenu = new System.Windows.Controls.ContextMenu();
                        CreateOption(item, "Delete", DeleteItem);
                        CreateOption(item, "Open", OpenItem);
                    }
                }
            }
        }

        private void status_bar(object sender, RoutedEventArgs e)
        {
            TreeViewItem selectedItem = (TreeViewItem)view.SelectedItem;
            char[] rahs = { '-', '-', '-', '-' };
            FileAttributes fileAttributes = new FileInfo((string)selectedItem.Tag).Attributes;

            if ((fileAttributes & FileAttributes.ReadOnly) == FileAttributes.ReadOnly) rahs[0] = 'R';
            if ((fileAttributes & FileAttributes.Archive) == FileAttributes.Archive) rahs[1] = 'A';
            if ((fileAttributes & FileAttributes.Hidden) == FileAttributes.Hidden) rahs[2] = 'H';
            if ((fileAttributes & FileAttributes.System) == FileAttributes.System) rahs[3] = 'S';
            DOS.Text = new string(rahs);
        }

        private void OpenItem(object sender, RoutedEventArgs e)
        {
            TreeViewItem selectedItem = (TreeViewItem)view.SelectedItem;
            string txt = File.ReadAllText((string)selectedItem.Tag);
            contentView.Content = new TextBlock() { Text = txt };
            Trace.WriteLine("Open");
        }
    }
}
