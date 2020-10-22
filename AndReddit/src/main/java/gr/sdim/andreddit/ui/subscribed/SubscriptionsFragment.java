package gr.sdim.andreddit.ui.subscribed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import gr.sdim.andreddit.R;
import gr.sdim.andreddit.data.SubscriptionsAdapter;
import gr.sdim.andreddit.databinding.FragmentSubscribedBinding;
import gr.sdim.andreddit.databinding.SubredditListItemViewBinding;

public class SubscriptionsFragment extends Fragment {

    FragmentSubscribedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSubscribedBinding.inflate(getLayoutInflater(),container,false);
        String testJson = "{\"kind\": \"Listing\", \"data\": {\"modhash\": \"yet5zwyzexa6f90b83dcb1b2dd4dc3495b370da8d74d10990e\", \"dist\": 4, \"children\": [{\"kind\": \"t5\", \"data\": {\"user_flair_background_color\": null, \"submit_text_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;&lt;a href=\\\"/r/Help\\\"&gt;r/Help&lt;/a&gt; is for help with Reddit, that is,  &lt;strong&gt;this is a community for Reddit Tech Support&lt;/strong&gt;.   If your submission is &lt;strong&gt;not&lt;/strong&gt; about Reddit, then please look for your help in &lt;a href=\\\"/r/self\\\"&gt;r/self&lt;/a&gt;, &lt;a href=\\\"/r/AskReddit\\\"&gt;r/AskReddit&lt;/a&gt;, &lt;a href=\\\"/r/needadvice\\\"&gt;r/needadvice&lt;/a&gt; or another similar subreddit. &lt;/p&gt;\\n\\n&lt;p&gt;If it&amp;#39;s help with reddit you are looking for, then please look at the following:&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"http://www.reddit.com/r/help/about/sidebar\\\"&gt;Our /r/help sidebar&lt;/a&gt; contains a lot of links.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"http://www.reddit.com/r/help/wiki/faq\\\"&gt;Our /r/help FAQ&lt;/a&gt; and the &lt;a href=\\\"http://www.reddit.com/wiki/faq\\\"&gt;general reddit FAQ&lt;/a&gt; both contain a lot of basic information. &lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"http://www.reddit.com/wiki/reddiquette\\\"&gt;The reddiquette&lt;/a&gt; may also help to answer your question. &lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;p&gt;If you still need help with reddit after reading those, then please submit a question and we&amp;#39;ll try and find somebody to answer the question.&lt;/p&gt;\\n\\n&lt;p&gt;Thank you. &lt;/p&gt;\\n\\n&lt;p&gt;-- Your friendly neighborhood &lt;a href=\\\"/r/help\\\"&gt;/r/help&lt;/a&gt; mod-staff.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"restrict_posting\": true, \"user_is_banned\": false, \"free_form_reports\": true, \"wiki_enabled\": true, \"user_is_muted\": false, \"user_can_flair_in_sr\": null, \"display_name\": \"help\", \"header_img\": \"https://a.thumbs.redditmedia.com/QuMkphJAV_9CycJx.png\", \"title\": \"For all your questions about Reddit!\", \"allow_galleries\": true, \"icon_size\": [256, 256], \"primary_color\": \"#ff5700\", \"active_user_count\": null, \"icon_img\": \"https://b.thumbs.redditmedia.com/nPovHg8P5YoA0cZiRJu2eK1smx_FZa45MeLa6Wxkk_M.png\", \"display_name_prefixed\": \"r/help\", \"accounts_active\": null, \"public_traffic\": false, \"subscribers\": 372220, \"user_flair_richtext\": [], \"name\": \"t5_2qh4a\", \"quarantine\": false, \"hide_ads\": false, \"emojis_enabled\": true, \"advertiser_category\": \"\", \"public_description\": \"A subreddit to ask questions (and get answers) about Reddit.\", \"comment_score_hide_mins\": 0, \"allow_predictions\": false, \"user_has_favorited\": false, \"user_flair_template_id\": null, \"community_icon\": \"https://styles.redditmedia.com/t5_2qh4a/styles/communityIcon_z9ukzp0n9m021.png?width=256&amp;s=7a29e3b7106333a479c6e3ce18e5945fbc8e0491\", \"banner_background_image\": \"\", \"original_content_tag_enabled\": false, \"submit_text\": \"r/Help is for help with Reddit, that is,  **this is a community for Reddit Tech Support**.   If your submission is **not** about Reddit, then please look for your help in r/self, r/AskReddit, r/needadvice or another similar subreddit. \\n\\nIf it's help with reddit you are looking for, then please look at the following:\\n\\n* [Our /r/help sidebar](http://www.reddit.com/r/help/about/sidebar) contains a lot of links.  \\n* [Our /r/help FAQ](http://www.reddit.com/r/help/wiki/faq) and the [general reddit FAQ](http://www.reddit.com/wiki/faq) both contain a lot of basic information. \\n* [The reddiquette](http://www.reddit.com/wiki/reddiquette) may also help to answer your question. \\n\\nIf you still need help with reddit after reading those, then please submit a question and we'll try and find somebody to answer the question.\\n\\nThank you. \\n\\n-- Your friendly neighborhood /r/help mod-staff.\", \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;&lt;em&gt;For your questions about Reddit only, please.&lt;/em&gt;&lt;/p&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;h3&gt;Check the &lt;a href=\\\"http://www.reddit.com/r/help/wiki/FAQ\\\"&gt;FAQ&lt;/a&gt; or the &lt;a href=\\\"https://www.reddithelp.com\\\"&gt;Reddit Help Center&lt;/a&gt; to see if your question has already been answered.&lt;/h3&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;p&gt;&lt;strong&gt;&lt;a href=\\\"/wiki/reddit_101\\\"&gt;New to reddit? click here!&lt;/a&gt;&lt;/strong&gt;&lt;/p&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;h3&gt;&lt;a href=\\\"http://www.redditstatus.com/\\\"&gt;reddit status&lt;/a&gt; -- status page for checking site health&lt;/h3&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;p&gt;&lt;strong&gt;Is your content not showing on reddit?&lt;/strong&gt;&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;p&gt;If you are a new user/trying to submit a post in a reddit you have not submitted to before, please take some time to first participate in that reddit (browse, upvote content/comments etc).&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;If you have tried the above and your content still does not show, please contact a moderator &lt;strong&gt;in the reddit where you&amp;#39;re having problems&lt;/strong&gt;. See the &lt;a href=\\\"http://www.reddit.com/r/help/wiki/faq\\\"&gt;FAQ&lt;/a&gt; for more information.&lt;/p&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;h3&gt;For other questions not specific to reddit, try:&lt;/h3&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/AskReddit\\\"&gt;/r/AskReddit&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/Advice\\\"&gt;/r/Advice&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/needadvice\\\"&gt;/r/needadvice&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/techsupport\\\"&gt;/r/techsupport&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/relationship_advice\\\"&gt;/r/relationship_advice&lt;/a&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;h3&gt;Other helpful subreddits:&lt;/h3&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/Bugs\\\"&gt;/r/Bugs&lt;/a&gt; - If you have found a possible bug in reddit. &lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/goldbenefits\\\"&gt;/r/goldbenefits&lt;/a&gt; - Check out new gold features and reddit gold partners&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/RedditMobile\\\"&gt;/r/RedditMobile&lt;/a&gt; - Official reddit mobile apps&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/MobileWeb\\\"&gt;/r/MobileWeb&lt;/a&gt; - Reddit mobile website&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/Enhancement\\\"&gt;/r/Enhancement&lt;/a&gt; - Reddit Enhancement Suite (RES) from &lt;a href=\\\"/u/honestbleeps\\\"&gt;/u/honestbleeps&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/RESissues\\\"&gt;/r/RESissues&lt;/a&gt; - For your RES problems&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/redditrequest\\\"&gt;/r/redditrequest&lt;/a&gt; - Request to moderate an abandoned subreddit or request a subreddit be unbanned &lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/adoptareddit\\\"&gt;/r/adoptareddit&lt;/a&gt; - Give away or acquire an unused subreddit.&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/needamod\\\"&gt;/r/needamod&lt;/a&gt; - Need help moderating a reddit? Want to volunteer?&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/AskModerators\\\"&gt;/r/AskModerators&lt;/a&gt; - for general questions aimed at moderators of reddit.&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/modhelp\\\"&gt;/r/modhelp&lt;/a&gt; - Help for questions about moderation.&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/csshelp\\\"&gt;/r/csshelp&lt;/a&gt; - subreddit style help&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/modsupport\\\"&gt;/r/modsupport&lt;/a&gt; - a point of contact for moderators to discuss issues with reddit admins, mostly about mod tools&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/modclub\\\"&gt;/r/modclub&lt;/a&gt; A place for mods of communities with 100 or more users to hang out&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/i18n\\\"&gt;/r/i18n&lt;/a&gt; - help translate reddit!&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/ideasfortheadmins\\\"&gt;/r/ideasfortheadmins&lt;/a&gt; - suggestions to improve Reddit&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/FindAReddit\\\"&gt;/r/FindAReddit&lt;/a&gt; - Looking for a subreddit on a particular topic but don&amp;#39;t know where to start?&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/FindASubreddit\\\"&gt;/r/FindASubreddit&lt;/a&gt; - Looking for a subreddit on a particular topic but don&amp;#39;t know where to start?&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/aboutreddit\\\"&gt;/r/aboutreddit&lt;/a&gt; - for submissions about Reddit.&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/TheoryOfReddit\\\"&gt;/r/TheoryOfReddit&lt;/a&gt; - to discuss Reddit. &lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;h3&gt;The lightbulb icons next to usernames indicate people who have demonstrated a history of providing useful and helpful answers in this subreddit.&lt;/h3&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;ol&gt;\\n&lt;li&gt;&lt;a href=\\\"http://www.reddit.com/r/help/wiki/faq\\\"&gt;Be sure to check the information in the sidebar and the FAQ! &lt;/a&gt;&lt;/li&gt;\\n&lt;/ol&gt;\\n\\n&lt;p&gt;&lt;a href=\\\"http://webchat.snoonet.org/#reddit-help\\\"&gt;Join us in IRC #reddit-help on irc.snoonet.org&lt;/a&gt;&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"spoilers_enabled\": true, \"header_title\": \"Welcome snoobies, ask away! (thanks atomic1fire)\", \"header_size\": [135, 40], \"user_flair_position\": \"right\", \"all_original_content\": false, \"has_menu_widget\": false, \"is_enrolled_in_new_modmail\": null, \"key_color\": \"#ff4500\", \"can_assign_user_flair\": false, \"created\": 1201273429.0, \"wls\": 6, \"show_media_preview\": true, \"submission_type\": \"self\", \"user_is_subscriber\": true, \"disable_contributor_requests\": false, \"allow_videogifs\": false, \"user_flair_type\": \"text\", \"allow_polls\": false, \"collapse_deleted_comments\": false, \"emojis_custom_size\": null, \"public_description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;A subreddit to ask questions (and get answers) about Reddit.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"allow_videos\": false, \"is_crosspostable_subreddit\": null, \"notification_level\": \"low\", \"can_assign_link_flair\": true, \"accounts_active_is_fuzzed\": false, \"submit_text_label\": \"Ask a question about Reddit\", \"link_flair_position\": \"left\", \"user_sr_flair_enabled\": null, \"user_flair_enabled_in_sr\": false, \"allow_discovery\": true, \"user_sr_theme_enabled\": true, \"link_flair_enabled\": true, \"subreddit_type\": \"public\", \"suggested_comment_sort\": null, \"banner_img\": \"\", \"user_flair_text\": null, \"banner_background_color\": \"\", \"show_media\": false, \"id\": \"2qh4a\", \"user_is_moderator\": false, \"over18\": false, \"description\": \"*For your questions about Reddit only, please.*\\n\\n---\\n\\n### Check the [FAQ](http://www.reddit.com/r/help/wiki/FAQ) or the [Reddit Help Center](https://www.reddithelp.com) to see if your question has already been answered.\\n\\n\\n---\\n\\n  **[New to reddit? click here!](/wiki/reddit_101)**\\n\\n\\n---\\n\\n\\n###[reddit status](http://www.redditstatus.com/) -- status page for checking site health\\n\\n---\\n\\n**Is your content not showing on reddit?**\\n\\n- If you are a new user/trying to submit a post in a reddit you have not submitted to before, please take some time to first participate in that reddit (browse, upvote content/comments etc).\\n\\n- If you have tried the above and your content still does not show, please contact a moderator **in the reddit where you're having problems**. See the [FAQ](http://www.reddit.com/r/help/wiki/faq) for more information.\\n\\n\\n---\\n\\n###For other questions not specific to reddit, try:\\n\\n* /r/AskReddit\\n* /r/Advice\\n* /r/needadvice\\n* /r/techsupport\\n* /r/relationship_advice\\n\\n###Other helpful subreddits:\\n\\n* /r/Bugs - If you have found a possible bug in reddit. \\n* /r/goldbenefits - Check out new gold features and reddit gold partners\\n* /r/RedditMobile - Official reddit mobile apps\\n* /r/MobileWeb - Reddit mobile website\\n* /r/Enhancement - Reddit Enhancement Suite (RES) from /u/honestbleeps\\n* /r/RESissues - For your RES problems\\n* /r/redditrequest - Request to moderate an abandoned subreddit or request a subreddit be unbanned \\n* /r/adoptareddit - Give away or acquire an unused subreddit.\\n* /r/needamod - Need help moderating a reddit? Want to volunteer?\\n* /r/AskModerators - for general questions aimed at moderators of reddit.\\n* /r/modhelp - Help for questions about moderation.\\n* /r/csshelp - subreddit style help\\n* /r/modsupport - a point of contact for moderators to discuss issues with reddit admins, mostly about mod tools  \\n* /r/modclub A place for mods of communities with 100 or more users to hang out\\n* /r/i18n - help translate reddit!\\n* /r/ideasfortheadmins - suggestions to improve Reddit\\n* /r/FindAReddit - Looking for a subreddit on a particular topic but don't know where to start?\\n* /r/FindASubreddit - Looking for a subreddit on a particular topic but don't know where to start?\\n* /r/aboutreddit - for submissions about Reddit.\\n* /r/TheoryOfReddit - to discuss Reddit. \\n\\n***\\n\\n###The lightbulb icons next to usernames indicate people who have demonstrated a history of providing useful and helpful answers in this subreddit. \\n\\n***\\n\\n 1. [Be sure to check the information in the sidebar and the FAQ! ](http://www.reddit.com/r/help/wiki/faq)\\n\\n[Join us in IRC #reddit-help on irc.snoonet.org](http://webchat.snoonet.org/#reddit-help)\", \"submit_link_label\": \"\", \"user_flair_text_color\": null, \"restrict_commenting\": false, \"user_flair_css_class\": null, \"allow_images\": false, \"lang\": \"en\", \"whitelist_status\": \"all_ads\", \"url\": \"/r/help/\", \"created_utc\": 1201244629.0, \"banner_size\": null, \"mobile_banner_image\": \"\", \"user_is_contributor\": false}}, {\"kind\": \"t5\", \"data\": {\"user_flair_background_color\": null, \"submit_text_html\": null, \"restrict_posting\": true, \"user_is_banned\": false, \"free_form_reports\": true, \"wiki_enabled\": null, \"user_is_muted\": false, \"user_can_flair_in_sr\": null, \"display_name\": \"announcements\", \"header_img\": null, \"title\": \"Announcements\", \"allow_galleries\": true, \"icon_size\": [256, 256], \"primary_color\": \"#fc471e\", \"active_user_count\": null, \"icon_img\": \"https://b.thumbs.redditmedia.com/iTldIIlQVSoH6SPlH9iiPZZVzFWubJU7cOM__uqSOqU.png\", \"display_name_prefixed\": \"r/announcements\", \"accounts_active\": null, \"public_traffic\": false, \"subscribers\": 72535234, \"user_flair_richtext\": [], \"videostream_links_count\": 0, \"name\": \"t5_2r0ij\", \"quarantine\": false, \"hide_ads\": false, \"emojis_enabled\": false, \"advertiser_category\": \"Lifestyles\", \"public_description\": \"Official announcements from Reddit, Inc.\", \"comment_score_hide_mins\": 0, \"allow_predictions\": false, \"user_has_favorited\": false, \"user_flair_template_id\": null, \"community_icon\": \"https://styles.redditmedia.com/t5_2r0ij/styles/communityIcon_yor9myhxz5x11.png?width=256&amp;s=897f8538fb9de5be72e13970788816a27cd7bd0e\", \"banner_background_image\": \"https://styles.redditmedia.com/t5_2r0ij/styles/bannerBackgroundImage_6gx1wewyz5x11.jpg?width=4000&amp;s=37b4680950249d35adda9210d3440f181543491b\", \"original_content_tag_enabled\": false, \"submit_text\": \"\", \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Official announcements from the reddit admins.&lt;/p&gt;\\n\\n&lt;p&gt;See also:&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/blog\\\"&gt;r/blog&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/changelog\\\"&gt;r/changelog&lt;/a&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;p&gt;To report a site-wide rule violation to the Reddit Admins, please use our &lt;a href=\\\"https://www.reddit.com/report\\\"&gt;report forms&lt;/a&gt; or message &lt;a href=\\\"https://www.reddit.com/message/compose?to=%2Fr%2Freddit.com\\\"&gt;/r/reddit.com modmail&lt;/a&gt;.&lt;/p&gt;\\n\\n&lt;p&gt;This is an admin-sponsored subreddit.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"spoilers_enabled\": true, \"header_title\": \"\", \"header_size\": null, \"user_flair_position\": \"right\", \"all_original_content\": false, \"has_menu_widget\": false, \"is_enrolled_in_new_modmail\": null, \"key_color\": \"#ff4500\", \"can_assign_user_flair\": false, \"created\": 1245284901.0, \"wls\": 6, \"show_media_preview\": true, \"submission_type\": \"any\", \"user_is_subscriber\": true, \"disable_contributor_requests\": true, \"allow_videogifs\": true, \"user_flair_type\": \"text\", \"allow_polls\": true, \"collapse_deleted_comments\": false, \"emojis_custom_size\": null, \"public_description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Official announcements from Reddit, Inc.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"allow_videos\": true, \"is_crosspostable_subreddit\": true, \"notification_level\": \"low\", \"can_assign_link_flair\": false, \"accounts_active_is_fuzzed\": false, \"submit_text_label\": \"\", \"link_flair_position\": \"right\", \"user_sr_flair_enabled\": null, \"user_flair_enabled_in_sr\": false, \"allow_discovery\": true, \"user_sr_theme_enabled\": true, \"link_flair_enabled\": true, \"subreddit_type\": \"restricted\", \"suggested_comment_sort\": \"qa\", \"banner_img\": \"\", \"user_flair_text\": null, \"banner_background_color\": \"\", \"show_media\": true, \"id\": \"2r0ij\", \"user_is_moderator\": false, \"over18\": false, \"description\": \"Official announcements from the reddit admins.\\n\\nSee also:\\n   \\n* r/blog\\n* r/changelog\\n\\nTo report a site-wide rule violation to the Reddit Admins, please use our [report forms](https://www.reddit.com/report) or message [/r/reddit.com modmail](https://www.reddit.com/message/compose?to=%2Fr%2Freddit.com).\\n\\nThis is an admin-sponsored subreddit.\", \"submit_link_label\": \"\", \"user_flair_text_color\": null, \"restrict_commenting\": false, \"user_flair_css_class\": null, \"allow_images\": true, \"lang\": \"en\", \"whitelist_status\": \"all_ads\", \"url\": \"/r/announcements/\", \"created_utc\": 1245256101.0, \"banner_size\": null, \"mobile_banner_image\": \"\", \"user_is_contributor\": false}}, {\"kind\": \"t5\", \"data\": {\"user_flair_background_color\": null, \"submit_text_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;h2&gt;Read the &lt;a href=\\\"https://www.reddit.com/r/androiddev/about/rules/\\\"&gt;rules&lt;/a&gt;!&lt;/h2&gt;\\n\\n&lt;p&gt;&lt;strong&gt;App takedowns, play store vents, and &amp;quot;help me&amp;quot;/one-off question posts will be removed.&lt;/strong&gt;&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"restrict_posting\": true, \"user_is_banned\": false, \"free_form_reports\": true, \"wiki_enabled\": true, \"user_is_muted\": false, \"user_can_flair_in_sr\": null, \"display_name\": \"androiddev\", \"header_img\": null, \"title\": \"Developing Android Apps\", \"allow_galleries\": false, \"icon_size\": [256, 256], \"primary_color\": \"#3ddb86\", \"active_user_count\": null, \"icon_img\": \"https://b.thumbs.redditmedia.com/ZULq8SFqJMtVQvcmDYcMqEmKr-cpsnqIEomHpOAgrTk.png\", \"display_name_prefixed\": \"r/androiddev\", \"accounts_active\": null, \"public_traffic\": false, \"subscribers\": 159508, \"user_flair_richtext\": [], \"videostream_links_count\": 0, \"name\": \"t5_2r26y\", \"quarantine\": false, \"hide_ads\": false, \"emojis_enabled\": true, \"advertiser_category\": \"Technology\", \"public_description\": \"News for Android developers with the who, what, where when and how of the Android community. Probably mostly the how.\\n\\nHere, you'll find:\\n\\n- News for Android developers\\n- Thoughtful, informative articles\\n- Insightful talks and presentations\\n- Useful libraries\\n- Handy tools\\n- Open source applications for studying\", \"comment_score_hide_mins\": 0, \"allow_predictions\": false, \"user_has_favorited\": false, \"user_flair_template_id\": null, \"community_icon\": \"https://styles.redditmedia.com/t5_2r26y/styles/communityIcon_q69d9lxagoi31.png?width=256&amp;s=84366e1c674b27a5d850effae3fe2d6ba408a8c0\", \"banner_background_image\": \"https://styles.redditmedia.com/t5_2r26y/styles/bannerBackgroundImage_t7mdhxs5vdg01.png?width=4000&amp;s=8f37bcc9932c344b7f9026198e78d12d09119d75\", \"original_content_tag_enabled\": false, \"submit_text\": \"## Read the [rules](https://www.reddit.com/r/androiddev/about/rules/)!\\n\\n**App takedowns, play store vents, and \\\"help me\\\"/one-off question posts will be removed.**\", \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;h2&gt;About&lt;/h2&gt;\\n\\n&lt;p&gt;News for Android developers with the who, what, where, when, and how of the Android community. Probably mostly the how.&lt;/p&gt;\\n\\n&lt;p&gt;Here, you&amp;#39;ll find:&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;News for Android developers&lt;/li&gt;\\n&lt;li&gt;Thoughtful, informative articles&lt;/li&gt;\\n&lt;li&gt;Insightful talks and presentations&lt;/li&gt;\\n&lt;li&gt;Useful libraries&lt;/li&gt;\\n&lt;li&gt;Handy tools&lt;/li&gt;\\n&lt;li&gt;Open source applications for studying&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;h2&gt;AMA Calendar (&lt;a href=\\\"https://www.reddit.com/r/androiddev/wiki/ama\\\"&gt;wiki&lt;/a&gt;)&lt;/h2&gt;\\n\\n&lt;table&gt;&lt;thead&gt;\\n&lt;tr&gt;\\n&lt;th align=\\\"center\\\"&gt;Date&lt;/th&gt;\\n&lt;th align=\\\"center\\\"&gt;Time&lt;/th&gt;\\n&lt;th align=\\\"center\\\"&gt;Who&lt;/th&gt;\\n&lt;th align=\\\"center\\\"&gt;What&lt;/th&gt;\\n&lt;th align=\\\"center\\\"&gt;Link&lt;/th&gt;\\n&lt;/tr&gt;\\n&lt;/thead&gt;&lt;tbody&gt;\\n&lt;/tbody&gt;&lt;/table&gt;\\n\\n&lt;h2&gt;Weekly Threads Calendar&lt;/h2&gt;\\n\\n&lt;p&gt;Autoposted at approx 9AM EST / 2PM GMT&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"https://www.reddit.com/r/androiddev/search/?q=author%3AAutoModerator+%22who%27s+hiring%22&amp;amp;sort=new&amp;amp;restrict_sr=on&amp;amp;t=all\\\"&gt;Monday: Who&amp;#39;s Hiring&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"https://www.reddit.com/r/androiddev/search/?q=author%3AAutoModerator+%22weekly%20questions%20thread%22&amp;amp;sort=new&amp;amp;restrict_sr=on&amp;amp;t=all\\\"&gt;Wednesday: Questions&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"https://www.reddit.com/r/androiddev/search/?q=author%3AAutoModerator+%22anything%20goes%22&amp;amp;sort=new&amp;amp;restrict_sr=on&amp;amp;t=all\\\"&gt;Friday: Anything Goes&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"https://www.reddit.com/r/androiddev/search?q=author%3AAutoModerator+%22app+feedback+thread%22+NOT+%22anything+goes%22+NOT+%22weekly+questions%22&amp;amp;restrict_sr=on&amp;amp;sort=new&amp;amp;t=all\\\"&gt;Saturday: App Feedback&lt;/a&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;h2&gt;Links&lt;/h2&gt;\\n\\n&lt;h3&gt;&lt;a href=\\\"https://www.reddit.com/r/androiddev/about/rules\\\"&gt;Rules!&lt;/a&gt;&lt;/h3&gt;\\n\\n&lt;h3&gt;&lt;a href=\\\"http://www.reddit.com/r/androiddev/wiki/\\\"&gt;Wiki and FAQ!&lt;/a&gt;&lt;/h3&gt;\\n\\n&lt;h3&gt;&lt;a href=\\\"https://discord.gg/D2cNrqX\\\"&gt;Discord!&lt;/a&gt;&lt;/h3&gt;\\n\\n&lt;h5&gt;&lt;a href=\\\"http://www.reddit.com/r/androiddev\\\"&gt;&lt;/a&gt;&lt;/h5&gt;\\n\\n&lt;p&gt;&lt;a href=\\\"#/RES_SR_Config/NightModeCompatible\\\"&gt;&lt;/a&gt;&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"spoilers_enabled\": true, \"header_title\": \"\", \"header_size\": null, \"user_flair_position\": \"right\", \"all_original_content\": false, \"has_menu_widget\": false, \"is_enrolled_in_new_modmail\": null, \"key_color\": \"#46d160\", \"can_assign_user_flair\": true, \"created\": 1247453708.0, \"wls\": 6, \"show_media_preview\": true, \"submission_type\": \"any\", \"user_is_subscriber\": true, \"disable_contributor_requests\": false, \"allow_videogifs\": true, \"user_flair_type\": \"text\", \"allow_polls\": true, \"collapse_deleted_comments\": true, \"emojis_custom_size\": null, \"public_description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;News for Android developers with the who, what, where when and how of the Android community. Probably mostly the how.&lt;/p&gt;\\n\\n&lt;p&gt;Here, you&amp;#39;ll find:&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;News for Android developers&lt;/li&gt;\\n&lt;li&gt;Thoughtful, informative articles&lt;/li&gt;\\n&lt;li&gt;Insightful talks and presentations&lt;/li&gt;\\n&lt;li&gt;Useful libraries&lt;/li&gt;\\n&lt;li&gt;Handy tools&lt;/li&gt;\\n&lt;li&gt;Open source applications for studying&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"allow_videos\": true, \"is_crosspostable_subreddit\": null, \"notification_level\": \"low\", \"can_assign_link_flair\": true, \"accounts_active_is_fuzzed\": false, \"submit_text_label\": \"\", \"link_flair_position\": \"left\", \"user_sr_flair_enabled\": null, \"user_flair_enabled_in_sr\": false, \"allow_discovery\": true, \"user_sr_theme_enabled\": true, \"link_flair_enabled\": true, \"subreddit_type\": \"public\", \"suggested_comment_sort\": null, \"banner_img\": \"\", \"user_flair_text\": null, \"banner_background_color\": \"\", \"show_media\": true, \"id\": \"2r26y\", \"user_is_moderator\": false, \"over18\": false, \"description\": \"## About\\nNews for Android developers with the who, what, where, when, and how of the Android community. Probably mostly the how.\\n\\nHere, you'll find:\\n\\n- News for Android developers\\n- Thoughtful, informative articles\\n- Insightful talks and presentations\\n- Useful libraries\\n- Handy tools\\n- Open source applications for studying\\n\\n## AMA Calendar ([wiki](https://www.reddit.com/r/androiddev/wiki/ama))\\n|   Date  |  Time  |  Who   |   What  | Link |\\n|:--------:|:-------:|:-------:|:-------:|:-------:|\\n\\n## Weekly Threads Calendar \\nAutoposted at approx 9AM EST / 2PM GMT\\n\\n* [Monday: Who's Hiring](https://www.reddit.com/r/androiddev/search/?q=author%3AAutoModerator+%22who%27s+hiring%22&amp;sort=new&amp;restrict_sr=on&amp;t=all)\\n* [Wednesday: Questions](https://www.reddit.com/r/androiddev/search/?q=author%3AAutoModerator+%22weekly%20questions%20thread%22&amp;sort=new&amp;restrict_sr=on&amp;t=all)\\n* [Friday: Anything Goes](https://www.reddit.com/r/androiddev/search/?q=author%3AAutoModerator+%22anything%20goes%22&amp;sort=new&amp;restrict_sr=on&amp;t=all)\\n* [Saturday: App Feedback](https://www.reddit.com/r/androiddev/search?q=author%3AAutoModerator+%22app+feedback+thread%22+NOT+%22anything+goes%22+NOT+%22weekly+questions%22&amp;restrict_sr=on&amp;sort=new&amp;t=all)\\n\\n## Links\\n### [Rules!](https://www.reddit.com/r/androiddev/about/rules)\\n\\n### [Wiki and FAQ!](http://www.reddit.com/r/androiddev/wiki/)\\n\\n### [Discord!](https://discord.gg/D2cNrqX)\\n\\n#####[](http://www.reddit.com/r/androiddev)\\n\\n[](#/RES_SR_Config/NightModeCompatible)\", \"submit_link_label\": \"\", \"user_flair_text_color\": null, \"restrict_commenting\": false, \"user_flair_css_class\": null, \"allow_images\": true, \"lang\": \"en\", \"whitelist_status\": \"all_ads\", \"url\": \"/r/androiddev/\", \"created_utc\": 1247424908.0, \"banner_size\": null, \"mobile_banner_image\": \"\", \"user_is_contributor\": false}}, {\"kind\": \"t5\", \"data\": {\"user_flair_background_color\": null, \"submit_text_html\": null, \"restrict_posting\": true, \"user_is_banned\": false, \"free_form_reports\": true, \"wiki_enabled\": null, \"user_is_muted\": false, \"user_can_flair_in_sr\": null, \"display_name\": \"Udacity\", \"header_img\": null, \"title\": \"Sebastian Thrun's new online University\", \"allow_galleries\": true, \"icon_size\": null, \"primary_color\": \"\", \"active_user_count\": null, \"icon_img\": \"\", \"display_name_prefixed\": \"r/Udacity\", \"accounts_active\": null, \"public_traffic\": false, \"subscribers\": 3806, \"user_flair_richtext\": [], \"videostream_links_count\": 0, \"name\": \"t5_2tf86\", \"quarantine\": false, \"hide_ads\": false, \"emojis_enabled\": false, \"advertiser_category\": \"\", \"public_description\": \"\", \"comment_score_hide_mins\": 0, \"allow_predictions\": false, \"user_has_favorited\": false, \"user_flair_template_id\": null, \"community_icon\": \"\", \"banner_background_image\": \"\", \"original_content_tag_enabled\": false, \"submit_text\": \"\", \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;AI class teacher and Google car maker Dr. Sebastian Thrun has started a new on-line university called Udacity.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;a href=\\\"http://www.udacity.com\\\"&gt;http://www.udacity.com&lt;/a&gt;&lt;/p&gt;\\n\\n&lt;p&gt;Discussion here.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"spoilers_enabled\": true, \"header_title\": null, \"header_size\": null, \"user_flair_position\": \"right\", \"all_original_content\": false, \"has_menu_widget\": false, \"is_enrolled_in_new_modmail\": null, \"key_color\": \"\", \"can_assign_user_flair\": false, \"created\": 1327372734.0, \"wls\": 6, \"show_media_preview\": true, \"submission_type\": \"any\", \"user_is_subscriber\": true, \"disable_contributor_requests\": false, \"allow_videogifs\": true, \"user_flair_type\": \"text\", \"allow_polls\": true, \"collapse_deleted_comments\": false, \"emojis_custom_size\": null, \"public_description_html\": null, \"allow_videos\": true, \"is_crosspostable_subreddit\": true, \"notification_level\": \"low\", \"can_assign_link_flair\": false, \"accounts_active_is_fuzzed\": false, \"submit_text_label\": \"\", \"link_flair_position\": \"\", \"user_sr_flair_enabled\": null, \"user_flair_enabled_in_sr\": false, \"allow_chat_post_creation\": false, \"allow_discovery\": true, \"user_sr_theme_enabled\": true, \"link_flair_enabled\": false, \"subreddit_type\": \"public\", \"suggested_comment_sort\": null, \"banner_img\": \"\", \"user_flair_text\": null, \"banner_background_color\": \"\", \"show_media\": false, \"id\": \"2tf86\", \"user_is_moderator\": false, \"over18\": false, \"description\": \"AI class teacher and Google car maker Dr. Sebastian Thrun has started a new on-line university called Udacity.\\n\\nhttp://www.udacity.com\\n\\nDiscussion here.\", \"is_chat_post_feature_enabled\": true, \"submit_link_label\": \"\", \"user_flair_text_color\": null, \"restrict_commenting\": false, \"user_flair_css_class\": null, \"allow_images\": true, \"lang\": \"en\", \"whitelist_status\": \"all_ads\", \"url\": \"/r/Udacity/\", \"created_utc\": 1327343934.0, \"banner_size\": null, \"mobile_banner_image\": \"\", \"user_is_contributor\": false}}], \"after\": null, \"before\": null}}";
        SubscriptionsAdapter adapter = new SubscriptionsAdapter(testJson,getContext());
        binding.subscribedRecyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

}