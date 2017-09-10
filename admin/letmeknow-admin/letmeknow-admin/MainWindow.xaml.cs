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
using System.Reflection;
using letmeknow_admin.Services;

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
                else
                    AccountService.logout();
            };
        }

        private void tileExit_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void tileChangePassword_Click(object sender, RoutedEventArgs e)
        {
            openWindow("ChangePassword");
        }

        private void tileUser_Click(object sender, RoutedEventArgs e)
        {
            openWindow("SearchUser");
        }

        private void tileNotification_Click(object sender, RoutedEventArgs e)
        {
            openWindow("SearchNotification");
        }

        private void tileGroup_Click(object sender, RoutedEventArgs e)
        {
            openWindow("SearchGroup"); 
        }

        private void tileHandleReport_Click(object sender, RoutedEventArgs e)
        {
            openWindow("ComplaintManager");
        }

        private void tileVerify_Click(object sender, RoutedEventArgs e)
        {
            openWindow("ApplicationManager");
        }

        private void openWindow (string windowType)
        {
            var window = (MetroWindow) Assembly.Load("letmeknow-admin")
                                               .CreateInstance("letmeknow_admin." + windowType);
            this.Hide();
            try
            {
                window.ShowDialog();
            }
            catch {}
            this.Show();
        }

        private void tileSendMessage_Click(object sender, RoutedEventArgs e)
        {
            openWindow("SystemMessage");
        }
    }
}
