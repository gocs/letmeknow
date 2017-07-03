﻿using System;
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
                e.Result = AppService.login(((KeyValuePair < string, string >)e.Argument).Key, 
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
                    progressRing.IsActive = false;
                }));
                return;
            }
            if ((AppService.LoginResult)e.Result == AppService.LoginResult.SUCCESS)
            {
                Dispatcher.Invoke(new Action(() =>
                {
                    mainWindow = new MainWindow(username.Text);
                    mainWindow.Show();
                    this.Close();
                }));
            }
            else if ((AppService.LoginResult)e.Result == AppService.LoginResult.WRONG)
            {
                MessageBox.Show("用户名或密码错误！");
                Dispatcher.Invoke(new Action(() =>
                {
                    progressRing.IsActive = false;
                }));
            }
            else
            {
                MessageBox.Show("该用户没有足够的权限");
                Dispatcher.Invoke(new Action(() =>
                {
                    progressRing.IsActive = false;
                }));
            }
        }

        public Login()
        {
            InitializeComponent();
        }

        private void Tile_Click(object sender, RoutedEventArgs e)
        {
            progressRing.IsActive = true;
            BackgroundWorker loginWorker = new BackgroundWorker();
            loginWorker.DoWork += loginWorker_DoWork;
            loginWorker.RunWorkerCompleted += loginWorker_RunWorkerCompleted;
            loginWorker.RunWorkerAsync(new KeyValuePair<string, string>(username.Text, password.Password));
        }
    }
}
