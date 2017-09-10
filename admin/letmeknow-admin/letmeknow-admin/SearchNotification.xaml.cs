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
using letmeknow_admin.Services;

namespace letmeknow_admin
{
    /// <summary>
    /// SearchNotification.xaml 的交互逻辑
    /// </summary>
    public partial class SearchNotification : MetroWindow
    {
        public SearchNotification()
        {
            InitializeComponent();
        }

        private void tileSearch_Click(object sender, RoutedEventArgs e)
        {
            if (keyword.Text.Trim() == string.Empty) return;
            NotificationList notificationList = new NotificationList("搜索结果：" + keyword.Text,
                NotificationService.SearchNotificationByKeyword(keyword.Text));
            this.Hide();
            notificationList.ShowDialog();
            this.Close();
        }
    }
}
