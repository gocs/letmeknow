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
using letmeknow_admin.Services;

namespace letmeknow_admin
{
    /// <summary>
    /// GroupDetail.xaml 的交互逻辑
    /// </summary>
    public partial class GroupDetail : MetroWindow
    {
        private Group group;
        public GroupDetail(int id)
        {
            InitializeComponent();
            group = GroupService.getGroup(id);
            loadGroupInfo();

        }
        private void loadGroupInfo()
        {
            lblTitle.Content = string.Format("{0} (GroupID: {1})", group.groupName, group.groupId);
            if (group.icon == null)
            {
                GroupIcon.Source = new BitmapImage(new Uri(@"pack://siteoforigin:,,,/Resources/groupicon.PNG", UriKind.Absolute));
                tileDeleteIcon.Visibility = Visibility.Hidden;
            }
            else
            {
                GroupIcon.Source = HttpHelper.getImage(group.icon);
                tileDeleteIcon.Visibility = Visibility.Visible;
            }
            lblCreateTime.Content = group.createdAt;
            lblCategory.Content = group.category;
            lblStatus.Content = group.status.ToString();
            lblMaster.Content = group.master;
            lblMemberNum.Content = group.members;
            if (group.status == GroupStatus.BANNED)
            {
                tileBan.Title = "解冻通知组";
                BanIcon.Kind = MahApps.Metro.IconPacks.PackIconMaterialKind.Undo;
            }
            else if (group.status == GroupStatus.DELETED)
            {
                tileBan.Visibility = Visibility.Hidden;
                tileDelete.Title = "恢复通知组";
                DeleteIcon.Kind = MahApps.Metro.IconPacks.PackIconEntypoKind.LevelUp;
            }
            else
            {
                tileBan.Title = "冻结通知组";
                BanIcon.Kind = MahApps.Metro.IconPacks.PackIconMaterialKind.BlockHelper;
                tileBan.Visibility = Visibility.Visible;
                tileDelete.Title = "删除通知组";
                DeleteIcon.Kind = MahApps.Metro.IconPacks.PackIconEntypoKind.SquaredCross;
            }
            if (group.category == GroupCategory.PRIVATE)
            {
                tileLiftup.Title = "设置为公开组";
                tileLiftup.TitleFontSize = 14;
                UpIcon.Kind = MahApps.Metro.IconPacks.PackIconFontAwesomeKind.ArrowUp;
            }
            else
            {
                tileLiftup.Title = "设置为私密组";
                tileLiftup.TitleFontSize = 12;
                UpIcon.Kind = MahApps.Metro.IconPacks.PackIconFontAwesomeKind.ArrowDown;
            }
        }

        private void tileDelete_Click(object sender, RoutedEventArgs e)
        {
            if (group.status != GroupStatus.DELETED)
                GroupService.deleteGroup(ref group);
            else
                GroupService.recoverGroup(ref group);
            loadGroupInfo();
        }

        private void tileBan_Click(object sender, RoutedEventArgs e)
        {
            if (group.status == GroupStatus.BANNED)
                GroupService.unblockGroup(ref group);
            else
                GroupService.banGroup(ref group);
            loadGroupInfo();
        }

        private void tileNotifications_Click(object sender, RoutedEventArgs e)
        {
            NotificationList notificationList = new NotificationList("Admin发送的通知", NotificationService.SearchNotificationByGroup(group.groupId));
            notificationList.Show();
        }

        private void tileLiftup_Click(object sender, RoutedEventArgs e)
        {
            if (group.category == GroupCategory.PRIVATE)
                GroupService.toPublic(ref group);
            else
                GroupService.toPrivate(ref group);
            loadGroupInfo();
        }

        private void tileDeleteIcon_Click(object sender, RoutedEventArgs e)
        {
            GroupService.deleteIcon(ref group);
            loadGroupInfo();
        }

        private void tileGroups_Click(object sender, RoutedEventArgs e)
        {
            var userInGroup = new UserInGroup(group);
            userInGroup.Show();
        }
    }
}
