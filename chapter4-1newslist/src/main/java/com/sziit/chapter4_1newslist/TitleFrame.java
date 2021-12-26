package com.sziit.chapter4_1newslist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TitleFrame extends Fragment implements View.OnClickListener {
    private Button mBtnFrag1;
    private Button mBtnFrag2;
    private String[] strTitle = {
            "最新：新增确诊6例，新增无症状感染者40例",
            "美联储如期维持利率不变，明确进一步渐进加息"
    };
    private String[] strContent = {
            "    4月27日0—24时，31个省（自治区、直辖市）和新疆生产建设兵团报告新增确诊病例6例，其中3例为境外输入病例，3例为本土病例（黑龙江3例）；无新增死亡病例；新增疑似病例1例，为境外输入疑似病例（上海1例）。" +
                    "\n" +
                    "    “当日新增治愈出院病例81例，解除医学观察的密切接触者915人，重症病例减少2例。境外输入现有确诊病例552例（含重症病例21例），现有疑似病例6例。累计确诊病例1639例，累计治愈出院病例1087例，无死亡病例。\n" +
                    "　　\n" +
                    "截至4月27日24时，据31个省（自治区、直辖市）和新疆生产建设兵团报告，现有确诊病例648例（其中重症病例50例），累计治愈出院病例77555例，累计死亡病例4633例，累计报告确诊病例82836例，现有疑似病例9例。累计追踪到密切接触者731015人，尚在医学观察的密切接触者8014人。\n" +
                    "　　\n" +
                    "湖北无新增确诊病例，无新增治愈出院病例，无新增死亡病例，无现有确诊病例，无重症病例。累计治愈出院病例63616例（武汉46464例），累计死亡病例4512例（武汉3869例），累计确诊病例68128例（武汉50333例）。无新增疑似病例，无现有疑似病例。\n" +
                    "\n" +
                    "31个省（自治区、直辖市）和新疆生产建设兵团报告新增无症状感染者40例，其中境外输入无症状感染者3例；当日无转为确诊病例；当日解除医学观察17例（境外输入4例）；尚在医学观察无症状感染者997例（境外输入130例）。\n" +
                    "\n" +
                    "    让各国人民共享经济全球化和世界经济增长成果，需要顺应经济全球化的历史大势。经济全球化为世界经济增长提供了强劲动力，造福了世界各国人民，是社会生产力发展的客观要求和科技进步的必然结果。这个大势不可逆转，不依人的意志为转移。面对保护主义、单边主义抬头，面对多边主义和自由贸易体制受到冲击，唯有顺应大势，共同应对风险挑战，才是正确选择。中国是经济全球化的受益者，更是贡献者。过去40年中国经济发展是在开放条件下取得的，未来中国经济实现高质量发展也必须在更加开放条件下进行。这是中国基于发展需要作出的战略抉择，同时也是在以实际行动推动经济全球化造福世界各国人民。中国顺应经济全球化大势的务实行动充分证明，中国不仅发展了自己，也造福了世界。",
            "    北京时间11月9日凌晨3时，美联储发布议息决议声明，维持联邦基金利率在2.00%-2.25%不变，符合市场预期。今年以来，美联储已经加息3次，分别在3月、6月和9月。此前市场预期，今年美联储还将加息一次，时间在12月。" +
                    "\n" +
                    "    此次会议声明较上次未有较大变化，值得一提的是，声明强调家庭支出持续强劲增长，但是固定资产投资较今年稍早时候的快速增长有所缓和。" +
                    "\n" +
                    "    关于劳动力市场和通胀目标方面，声明指出仍需进一步加息，强调公开市场委员会希望进一步持续加息能将联邦基金利率提升至与经济活动扩张一致的水平，强劲的劳动力市场和中期内接近对称目标2%的通胀水平。这一表述较9月的立场更为“鹰派”，在9月原文中的表述为，随着货币政策的进一步渐进调整，中期内，经济活动将持续扩张，劳动力市场情况将持续强劲。通胀在中期稳定在委员会对称目标2%附近。"
    };

    private String TAG = "";

    //重写Fragment的onCreateView方法，通过LayoutInflater的inflate()方法将frament_title布局动态加载进来
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        initView(view);
        TAG = getClass().toString();
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    private void initView(View view) {
        mBtnFrag1 = (Button) view.findViewById(R.id.btn_frag1);
        mBtnFrag2 = (Button) view.findViewById(R.id.btn_frag2);

        mBtnFrag1.setOnClickListener(this);
        mBtnFrag2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_frag1:
                setContent(strTitle[0], strContent[0]);
                break;
            case R.id.btn_frag2:
                setContent(strTitle[1], strContent[1]);
                break;
        }
    }
    private void setContent(String strTitle, String strContent) {
        ContentFragment mContentFragment=(ContentFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_content);
        mContentFragment.setData(strTitle,strContent);
    }
}

