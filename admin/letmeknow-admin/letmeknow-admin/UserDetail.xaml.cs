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
using letmeknow_admin.Models;

namespace letmeknow_admin
{
    /// <summary>
    /// UserDetail.xaml 的交互逻辑
    /// </summary>
    public partial class UserDetail : MetroWindow
    {
        private User user;
        public UserDetail(int UID)
        {
            InitializeComponent();
            user = AppService.getUser(UID);
            loadUserInfo();
        }

        private void loadUserInfo()
        {
            lblTitle.Content = string.Format("{0} (UID: {1})", user.name, user.UID);
            UserIcon.Source = AppService.getImage(user.iconURL);
            lblRegisterTime.Content = user.registerTime;
            lblUserCategory.Content = user.category.ToString();
            lblUserStatus.Content = user.status.ToString();
            if (user.status == UserStatus.BANNED)
            {
                tileBan.Title = "解封用户";
                BanIcon.Kind = MahApps.Metro.IconPacks.PackIconMaterialKind.Undo;
            }
            else if (user.status == UserStatus.DELETED)
            {
                tileBan.Visibility = Visibility.Hidden;
                tileDelete.Title = "恢复用户";
                DeleteIcon.Kind = MahApps.Metro.IconPacks.PackIconEntypoKind.LevelUp;
            }
            else
            {
                tileBan.Title = "封禁用户";
                BanIcon.Kind = MahApps.Metro.IconPacks.PackIconMaterialKind.BlockHelper;
                tileBan.Visibility = Visibility.Visible;
                tileDelete.Title = "删除用户";
                DeleteIcon.Kind = MahApps.Metro.IconPacks.PackIconEntypoKind.SquaredCross;
            }
        }

        private void tileDelete_Click(object sender, RoutedEventArgs e)
        {
            if (user.status != UserStatus.DELETED)
                AppService.deleteUser(user);
            else
                AppService.recoverUser(user);
            loadUserInfo();
        }

        private void tileBan_Click(object sender, RoutedEventArgs e)
        {
            if (user.status == UserStatus.BANNED)
                AppService.unblockUser(user);
            else
                AppService.banUser(user);
            loadUserInfo();
        }

        private void tileNotifications_Click(object sender, RoutedEventArgs e)
        {
            NotificationList notificationList = new NotificationList("Admin发送的通知", AppService.SearchNotificationByUser(user.UID));
            notificationList.Show();
        }
    }
}
