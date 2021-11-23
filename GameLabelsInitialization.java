import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Robert Simionescu
 * Initializes all the labels for the squares on the gameboard
 */
public class GameLabelsInitialization
{
    /**
     * @author Yash Kapoor
     * Initializes all the labels on the south side of the board with their names and images.
     */
    public static void initializeSouthLabels(ArrayList<JLabel> jLabelList) {
        ImageIcon mediterraneanAvenue = new ImageIcon("Images/MediterraneanAvenue.PNG");
        Image ma = mediterraneanAvenue.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon mt = new ImageIcon(ma);

        ImageIcon balticAvenue = new ImageIcon("Images/BalticAvenue.PNG");
        Image ba = balticAvenue.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon b = new ImageIcon(ba);

        ImageIcon readingRailroad = new ImageIcon("Images/ReadingRailroad.PNG");
        Image rr = readingRailroad.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon r = new ImageIcon(rr);

        ImageIcon orientalAvenue = new ImageIcon("Images/OrientalAvenue.PNG");
        Image oa = orientalAvenue.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon o = new ImageIcon(oa);

        ImageIcon vermontAvenue = new ImageIcon("Images/VermontAvenue.PNG");
        Image vma = vermontAvenue.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon vm = new ImageIcon(vma);

        ImageIcon connecticutAvenue = new ImageIcon("Images/ConnecticutAvenue.PNG");
        Image ca = connecticutAvenue.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon c = new ImageIcon(ca);

        ImageIcon startingPoint = new ImageIcon("Images/StartingPoint.PNG");
        Image sp = startingPoint.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon s = new ImageIcon(sp);

        ImageIcon jail = new ImageIcon("Images/Jail.PNG");
        Image jl = jail.getImage().getScaledInstance(118, 140, Image.SCALE_DEFAULT);
        ImageIcon j = new ImageIcon(jl);


        JLabel spl = new JLabel();
        spl.setIcon(s);
        spl.setName("Initial Starting Point");
        jLabelList.add(0, spl);

        JLabel mal = new JLabel();
        mal.setIcon(mt);
        mal.setName("Mediterranean Avenue");
        jLabelList.add(1, mal);

        JLabel btal = new JLabel();
        btal.setIcon(b);
        btal.setName("Baltic Avenue");
        jLabelList.add(2, btal);

        JLabel rrl = new JLabel();
        rrl.setIcon(r);
        rrl.setName("Reading Railroad");
        jLabelList.add(3, rrl);

        JLabel oal = new JLabel();
        oal.setIcon(o);
        oal.setName("Oriental Avenue");
        jLabelList.add(4, oal);

        JLabel vtal = new JLabel();
        vtal.setIcon(vm);
        vtal.setName("Vermont Avenue");
        jLabelList.add(5, vtal);

        JLabel cal = new JLabel();
        cal.setIcon(c);
        cal.setName("Connecticut Avenue");
        jLabelList.add(6, cal);

        JLabel jll = new JLabel();
        jll.setIcon(j);
        jll.setName("Jail");
        jLabelList.add(7, jll);

    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the west side of the board with their names and images.
     */
    public static void initializeWestLabels(ArrayList<JLabel> jLabelList) {
        ImageIcon newYorkAvenue = new ImageIcon("Images/NewYorkAvenue.PNG");
        Image nya = newYorkAvenue.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon ny = new ImageIcon(nya);

        ImageIcon tennesseeAvenue = new ImageIcon("Images/TennesseeAvenue.PNG");
        Image ta = tennesseeAvenue.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon t = new ImageIcon(ta);

        ImageIcon stJamesPlace = new ImageIcon("Images/StJamesPlace.PNG");
        Image sjp = stJamesPlace.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon sj = new ImageIcon(sjp);

        ImageIcon pennsylvaniaRailroad = new ImageIcon("Images/PennsylvaniaRailroad.PNG");
        Image pr = pennsylvaniaRailroad.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon p = new ImageIcon(pr);

        ImageIcon virginiaAvenue = new ImageIcon("Images/VirginiaAvenue.PNG");
        Image vga = virginiaAvenue.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon vg = new ImageIcon(vga);

        ImageIcon statesAvenue = new ImageIcon("Images/StatesAvenue.PNG");
        Image sta = statesAvenue.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon st = new ImageIcon(sta);

        ImageIcon electricCompany = new ImageIcon("Images/ElectricCompany.PNG");
        Image ec = electricCompany.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon e = new ImageIcon(ec);

        ImageIcon stCharlesPlace = new ImageIcon("Images/StCharlesPlace.PNG");
        Image scp = stCharlesPlace.getImage().getScaledInstance(141, 88, Image.SCALE_DEFAULT);
        ImageIcon sc = new ImageIcon(scp);


        JLabel scpl = new JLabel();
        scpl.setIcon(sc);
        scpl.setName("St. Charles Place");
        jLabelList.add(8, scpl);

        JLabel ecl = new JLabel();
        ecl.setIcon(e);
        ecl.setName("Electric Company");
        jLabelList.add(9, ecl);

        JLabel sal = new JLabel();
        sal.setIcon(st);
        sal.setName("States Avenue");
        jLabelList.add(10, sal);

        JLabel vgal = new JLabel();
        vgal.setIcon(vg);
        vgal.setName("Virginia Avenue");
        jLabelList.add(11, vgal);

        JLabel prl = new JLabel();
        prl.setIcon(p);
        prl.setName("Pennsylvania Railroad");
        jLabelList.add(12, prl);

        JLabel sjpl = new JLabel();
        sjpl.setIcon(sj);
        sjpl.setName("St. James Place");
        jLabelList.add(13, sjpl);


        JLabel tal = new JLabel();
        tal.setIcon(t);
        tal.setName("Tennessee Avenue");
        jLabelList.add(14, tal);

        JLabel nyl = new JLabel();
        nyl.setIcon(ny);
        nyl.setName("New York Avenue");
        jLabelList.add(15, nyl);

    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the north side of the board with their names and images.
     */
    public static void initializeNorthLabels(ArrayList<JLabel> jLabelList) {

        ImageIcon kentuckyAvenue = new ImageIcon("Images/KentuckyAvenue.PNG");
        Image ka = kentuckyAvenue.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon k = new ImageIcon(ka);

        ImageIcon indianaAvenue = new ImageIcon("Images/IndianaAvenue.PNG");
        Image ina = indianaAvenue.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon in = new ImageIcon(ina);

        ImageIcon illinoisAvenue = new ImageIcon("Images/IllinoisAvenue.PNG");
        Image ia = illinoisAvenue.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon i = new ImageIcon(ia);

        ImageIcon boRailroad = new ImageIcon("Images/B. & O. Railroad.PNG");
        Image bor = boRailroad.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon bo = new ImageIcon(bor);

        ImageIcon atlanticAvenue = new ImageIcon("Images/AtlanticAvenue.PNG");
        Image aa = atlanticAvenue.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon a = new ImageIcon(aa);

        ImageIcon ventnorAvenue = new ImageIcon("Images/VentnorAvenue.PNG");
        Image va = ventnorAvenue.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon v = new ImageIcon(va);

        ImageIcon waterWorks = new ImageIcon("Images/WaterWorks.PNG");
        Image ww = waterWorks.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon w = new ImageIcon(ww);

        ImageIcon marvinGardens = new ImageIcon("Images/MarvinGardens.PNG");
        Image mg = marvinGardens.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon m = new ImageIcon(mg);

        ImageIcon goToJail = new ImageIcon("Images/GoToJail.PNG");
        Image gtj = goToJail.getImage().getScaledInstance(105, 140, Image.SCALE_DEFAULT);
        ImageIcon gt = new ImageIcon(gtj);


        JLabel kal = new JLabel();
        kal.setIcon(k);
        kal.setName("Kentucky Avenue");
        jLabelList.add(16, kal);

        JLabel inal = new JLabel();
        inal.setIcon(in);
        inal.setName("Indiana Avenue");
        jLabelList.add(17, inal);

        JLabel ial = new JLabel();
        ial.setIcon(i);
        ial.setName("Illinois Avenue");
        jLabelList.add(18, ial);

        JLabel borl = new JLabel();
        borl.setIcon(bo);
        borl.setName("B. & O. Railroad");
        jLabelList.add(19, borl);

        JLabel aal = new JLabel();
        aal.setIcon(a);
        aal.setName("Atlantic Avenue");
        jLabelList.add(20, aal);

        JLabel val = new JLabel();
        val.setIcon(v);
        val.setName("Ventnor Avenue");
        jLabelList.add(21, val);

        JLabel wwl = new JLabel();
        wwl.setIcon(w);
        wwl.setName("Water Works");
        jLabelList.add(22, wwl);

        JLabel mgl = new JLabel();
        mgl.setIcon(m);
        mgl.setName("Marvin Gardens");
        jLabelList.add(23, mgl);

        JLabel gtjl = new JLabel();
        gtjl.setIcon(gt);
        gtjl.setName("Go to Jail");
        jLabelList.add(24, gtjl);

    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the east side of the board with their names and images.
     */
    public static void initializeEastLabels(ArrayList<JLabel> jLabelList) {

        ImageIcon pacificAvenue = new ImageIcon("Images/PacificAvenue.PNG");
        Image pa = pacificAvenue.getImage().getScaledInstance(141, 117, Image.SCALE_DEFAULT);
        ImageIcon p = new ImageIcon(pa);

        ImageIcon northCarolinaAvenue = new ImageIcon("Images/NorthCarolinaAvenue.PNG");
        Image nca = northCarolinaAvenue.getImage().getScaledInstance(141, 117, Image.SCALE_DEFAULT);
        ImageIcon nc = new ImageIcon(nca);

        ImageIcon pennsylvaniaAvenue = new ImageIcon("Images/PennsylvaniaAvenue.PNG");
        Image pna = pennsylvaniaAvenue.getImage().getScaledInstance(141, 117, Image.SCALE_DEFAULT);
        ImageIcon pn = new ImageIcon(pna);

        ImageIcon shortLineRailroad = new ImageIcon("Images/ShortLineRailroad.PNG");
        Image slr = shortLineRailroad.getImage().getScaledInstance(141, 117, Image.SCALE_DEFAULT);
        ImageIcon sl = new ImageIcon(slr);

        ImageIcon parkPlace = new ImageIcon("Images/ParkPlace.PNG");
        Image park = parkPlace.getImage().getScaledInstance(141, 117, Image.SCALE_DEFAULT);
        ImageIcon pp = new ImageIcon(park);

        ImageIcon boardWalk = new ImageIcon("Images/BoardWalk.PNG");
        Image bdw = boardWalk.getImage().getScaledInstance(141, 117, Image.SCALE_DEFAULT);
        ImageIcon bd = new ImageIcon(bdw);


        JLabel pal = new JLabel();
        pal.setIcon(p);
        pal.setName("Pacific Avenue");
        jLabelList.add(25, pal);

        JLabel ncal = new JLabel();
        ncal.setIcon(nc);
        ncal.setName("North Carolina Avenue");
        jLabelList.add(26, ncal);

        JLabel plal = new JLabel();
        plal.setIcon(pn);
        plal.setName("Pennsylvania Avenue");
        jLabelList.add(27, plal);

        JLabel slrl = new JLabel();
        slrl.setIcon(sl);
        slrl.setName("Short Line Railroad");
        jLabelList.add(28, slrl);

        JLabel ppl = new JLabel();
        ppl.setIcon(pp);
        ppl.setName("Park Place");
        jLabelList.add(29, ppl);

        JLabel bwl = new JLabel();
        bwl.setIcon(bd);
        bwl.setName("Board Walk");
        jLabelList.add(30, bwl);

    }
}
