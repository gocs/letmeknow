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
            this.Closing += (sender, e) =>
            {
                if (MessageBox.Show("确认退出程序吗？", "确认退出", MessageBoxButton.YesNo) == MessageBoxResult.No)
                    e.Cancel = true;
            };
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

        private void tileNotification_Click(object sender, RoutedEventArgs e)
        {
            SearchNotification searchNotification = new SearchNotification();
            this.Hide();
            searchNotification.ShowDialog();
            this.Show();
        }

        private void tileGroup_Click(object sender, RoutedEventArgs e)
        {
            SearchGroup searchGroup = new SearchGroup();
            this.Hide();
            searchGroup.ShowDialog();
            this.Show();
        }

        private void tileHandleReport_Click(object sender, RoutedEventArgs e)
        {
            ComplaintManager complaintManager = new ComplaintManager();
            this.Hide();
            complaintManager.ShowDialog();
            this.Show();
        }

        private void tileVerify_Click(object sender, RoutedEventArgs e)
        {
            ApplicationManager applicationManager = new ApplicationManager();
            this.Hide();
            applicationManager.ShowDialog();
            this.Show();
        }
    }
}
