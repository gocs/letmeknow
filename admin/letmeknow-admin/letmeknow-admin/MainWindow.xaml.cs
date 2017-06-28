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
using System.Windows.Shapes;
using MahApps.Metro.Controls;

namespace letmeknow_admin
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : MetroWindow
    {
        public MainWindow(string username)
        {
            InitializeComponent();
            title.Content = "欢迎使用，" + username;
        }

        private void tileExit_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void tileChangePassword_Click(object sender, RoutedEventArgs e)
        {
            ChangePassword changePassword = new ChangePassword();
            changePassword.ShowDialog();
        }

        private void tileUser_Click(object sender, RoutedEventArgs e)
        {
            SearchUser searchUser = new SearchUser();
            this.Hide();
            searchUser.ShowDialog();
            this.Show();
        }
    }
}
