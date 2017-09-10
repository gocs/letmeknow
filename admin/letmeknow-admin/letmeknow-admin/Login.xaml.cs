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
using MahApps.Metro.Controls;
using System.ComponentModel;
using letmeknow_admin.Services;

namespace letmeknow_admin
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class Login : MetroWindow
    {
        private MainWindow mainWindow;

        private void loginWorker_DoWork(object sender, DoWorkEventArgs e)
        {
            try
            {
                e.Result = AccountService.login(((KeyValuePair < string, string >)e.Argument).Key, 
                    ((KeyValuePair < string, string >) e.Argument).Value);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void loginWorker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            if (e.Result == null)
            {
                Dispatcher.Invoke(new Action(() =>
                {
                    progressBar.IsIndeterminate = false;
                }));
                return;
            }
            if ((AccountService.LoginResult)e.Result == AccountService.LoginResult.SUCCESS)
            {
                Dispatcher.Invoke(new Action(() =>
                {
                    mainWindow = new MainWindow(username.Text);
                    mainWindow.Show();
                    this.Close();
                }));
            }
            else if ((AccountService.LoginResult)e.Result == AccountService.LoginResult.WRONG)
            {
                MessageBox.Show("用户名或密码错误！");
                Dispatcher.Invoke(new Action(() =>
                {
                    progressBar.IsIndeterminate = false;
                }));
            }
            else
            {
                MessageBox.Show("该用户没有足够的权限");
                Dispatcher.Invoke(new Action(() =>
                {
                    progressBar.IsIndeterminate = false;
                }));
            }
        }

        public Login()
        {
            InitializeComponent();
        }

        private void Tile_Click(object sender, RoutedEventArgs e)
        {
            progressBar.IsIndeterminate = true;
            BackgroundWorker loginWorker = new BackgroundWorker();
            loginWorker.DoWork += loginWorker_DoWork;
            loginWorker.RunWorkerCompleted += loginWorker_RunWorkerCompleted;
            loginWorker.RunWorkerAsync(new KeyValuePair<string, string>(username.Text, password.Password));
            //loginWorker.RunWorkerAsync(new KeyValuePair<string, string>("admin", "123456"));
            //mainWindow = new MainWindow(username.Text);
            //mainWindow.Show();
            //this.Close();
        }
    }
}
