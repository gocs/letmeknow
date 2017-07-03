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
            lblTitle.Content = string.Format("{0} (UID: {1})", user.username, user.userId);
            if (user.avatar == null)
            {
                UserIcon.Source = new BitmapImage(new Uri(@"pack://siteoforigin:,,,/Resources/usericon.PNG", UriKind.Absolute));
                tileDeleteIcon.Visibility = Visibility.Hidden;
            }
            else
            {
                UserIcon.Source = AppService.getImage(user.avatar);
                tileDeleteIcon.Visibility = Visibility.Visible;
            }
            lblRegisterTime.Content = user.created_at;
            lblUserCategory.Content = user.is_admin.ToString();
            lblUserStatus.Content = user.status.ToString();
            lblEmail.Content = user.email;
            lblPhone.Content = user.phone_num;
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
            if (user.is_admin == UserCategory.USER)
            {
                tileLiftup.Title = "提升为管理员";
                tileLiftup.TitleFontSize = 14;
                UpIcon.Kind = MahApps.Metro.IconPacks.PackIconFontAwesomeKind.ArrowUp;
            }
            else
            {
                tileLiftup.Title = "取消管理员权限";
                tileLiftup.TitleFontSize = 12;
                UpIcon.Kind = MahApps.Metro.IconPacks.PackIconFontAwesomeKind.ArrowDown;
            }
        }

        private void tileDelete_Click(object sender, RoutedEventArgs e)
        {
            if (user.status != UserStatus.DELETED)
                AppService.deleteUser(ref user);
            else
                AppService.recoverUser(ref user);
            loadUserInfo();
        }

        private void tileBan_Click(object sender, RoutedEventArgs e)
        {
            if (user.status == UserStatus.BANNED)
                AppService.unblockUser(ref user);
            else
                AppService.banUser(ref user);
            loadUserInfo();
        }

        private void tileNotifications_Click(object sender, RoutedEventArgs e)
        {
            NotificationList notificationList = new NotificationList("Admin发送的通知", AppService.SearchNotificationByUser(user.userId));
            notificationList.Show();
        }

        private void tileLiftup_Click(object sender, RoutedEventArgs e)
        {
            if (user.is_admin == UserCategory.USER)
                AppService.toAdmin(ref user);
            else
                AppService.toNormalUser(ref user);
            loadUserInfo();
        }

        private void tileDeleteIcon_Click(object sender, RoutedEventArgs e)
        {
            AppService.deleteIcon(ref user);
            loadUserInfo();
        }
    }
}
